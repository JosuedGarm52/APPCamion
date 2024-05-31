package com.example.camionapi.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.camionapi.database.CamionDatabase
import com.example.camionapi.repository.CombinedCamionRepository
import com.example.camionapi.utils.MyAppConfig
import kotlinx.coroutines.launch

class PortalActivityViewModel(
    private val repository: CombinedCamionRepository,
    private val camionDatabase: CamionDatabase,
    private val context: Context): ViewModel() {
    fun isConnected() = viewModelScope.launch {
        repository.checkConnection()
    }
    fun isTokenValid() = viewModelScope.launch {
        repository.verifyToken()
    }

    fun deleteDatabase() {
        viewModelScope.launch {
            camionDatabase.deleteDatabase(context)
        }
    }
}

class PortalActivityViewModelFactory(
    private val repository: CombinedCamionRepository,
    private val camionDatabase: CamionDatabase,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortalActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PortalActivityViewModel(repository, camionDatabase, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

