package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CamionRepository
import com.example.camionapi.repository.CombinedCamionRepository
import kotlinx.coroutines.flow.firstOrNull


class FirstFragmentViewModel(private val repository: CombinedCamionRepository) : ViewModel() {

    val camionKardex: LiveData<List<CamionItem>> = liveData {
        //val data = repository.getAllCamiones().firstOrNull() ?: emptyList()
        //emit(data)
    }
}

class FirstFragmentViewModelFactory(private val repository: CombinedCamionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
