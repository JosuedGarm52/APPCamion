package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CamionRepository
import kotlinx.coroutines.launch

class SecondFragmentViewModel (private val repository: CamionRepository): ViewModel() {

    fun insertCamion(camion: CamionItem) = viewModelScope.launch{
        //Log.d("TAG", "Paso por aqui")
        repository.insert(camion)
    }
    fun getCamionById(id: Int): LiveData<CamionItem?> {
        return liveData {
            emit(repository.getCamionById(id))
        }
    }
    fun updateCamion(camion: CamionItem) = viewModelScope.launch {
        repository.update(camion)
    }
    fun deleteCamionById(id: Int) = viewModelScope.launch {
        repository.deleteCamionById(id)
    }
}

class SecondFragmentViewModelFactory(private val repository: CamionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SecondFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}