package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CamionRepository

class FirstFragmentViewModel(private val repository: CamionRepository) : ViewModel(){
    val CamionKardex : LiveData<List<CamionItem>> = repository.allCamionKardex.asLiveData()
}
class FirstFragmentViewModelFactory(private val repository: CamionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}