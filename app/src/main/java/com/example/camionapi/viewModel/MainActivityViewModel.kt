package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CombinedCamionRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: CombinedCamionRepository) : ViewModel() {

    private val _isRegisterLinkClicked = MutableLiveData<Boolean>()
    val isRegisterLinkClicked: LiveData<Boolean> get() = _isRegisterLinkClicked

    fun onRegisterLinkClicked() {
        _isRegisterLinkClicked.value = true // Update the variable when clicked
    }

    fun isConnected() = viewModelScope.launch{
        repository.checkConnection()
    }
}
class MainActivityViewModelFactory(private val repository: CombinedCamionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}