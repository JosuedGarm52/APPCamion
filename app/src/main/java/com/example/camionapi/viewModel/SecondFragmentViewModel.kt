package com.example.camionapi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.models.camion.CamionRequest
import com.example.camionapi.repository.CamionRepository
import com.example.camionapi.repository.CombinedCamionRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SecondFragmentViewModel (private val repository: CombinedCamionRepository): ViewModel() {

    fun insertCamion(camion: CamionItem)  = viewModelScope.launch{
        //Log.d("TAG", "Paso por aqui")
        val camionRequest = CamionRequest(
            color = camion.color,
            matricula = camion.matricula,
            conductor = camion.conductor,
            operativo = camion.yearOperative,
            marca = camion.marca,
            modelo = camion.modelo,
            dimension = camion.dimension,
            tipo = camion.tipo
        )
        val gson = Gson()
        val camionJson = gson.toJson(camionRequest)
        Log.d("TAG", camionJson)
        repository.addCamion(camionRequest)
    }
    fun getCamionById(id: Int): LiveData<CamionItem?> {

        return liveData {
            emit(repository.getCamionById(id))
        }
    }
    fun updateCamion(camion: CamionItem) = viewModelScope.launch {
        val camionRequest = CamionRequest(
            ID = camion.ID,
            color = camion.color,
            matricula = camion.matricula,
            conductor = camion.conductor,
            operativo = camion.yearOperative,
            marca = camion.marca,
            modelo = camion.modelo,
            dimension = camion.dimension,
            tipo = camion.tipo
        )
        repository.updateCamion(camionRequest)
    }
    fun deleteCamionById(id: Int) = viewModelScope.launch {
        repository.deleteCamionById(id)
    }
}

class SecondFragmentViewModelFactory(private val repository: CombinedCamionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SecondFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}