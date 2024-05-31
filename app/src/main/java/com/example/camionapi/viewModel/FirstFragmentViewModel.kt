package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CamionRepository
import com.example.camionapi.repository.CombinedCamionRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class FirstFragmentViewModel(private val repository: CombinedCamionRepository) : ViewModel() {
    // Utiliza una variable mutable para almacenar los elementos del flujo
    private val _camionKardex = MutableLiveData<List<CamionItem>>()

    // Exponer el LiveData como una propiedad pública
    val camionKardex: LiveData<List<CamionItem>> get() = _camionKardex

    init {
        viewModelScope.launch {
            // Recolectar elementos del flujo y asignarlos a la variable mutable
            repository.getAllCamiones().collect {
                _camionKardex.value = it // Asigna los elementos del flujo a la variable mutable
            }
        }
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
