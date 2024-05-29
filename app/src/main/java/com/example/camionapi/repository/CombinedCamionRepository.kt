package com.example.camionapi.repository

import android.util.Log
import com.example.camionapi.models.camion.CamionDao
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.network.CamionApi
import com.example.camionapi.utils.MyAppConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

class CombinedCamionRepository(
    private val retrofitApi: CamionApi,
    private val localRepository: CamionRepository
) {
    // Método para obtener todos los camiones, primero intenta obtenerlos de la API,
    // si falla, los obtiene de la base de datos local
    suspend fun getAllCamiones(): Flow<List<CamionItem>> {

        try {
            val response = retrofitApi.getAllCamiones()
            if (response.isSuccessful) {
                return response.body()?.let { flowOf(it) } ?: flowOf(emptyList())
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
        // Si falla la llamada a la API o no hay datos, obtiene los datos de la base de datos local
        return localRepository.allCamionKardex ?: flowOf(emptyList())
    }

    suspend fun checkConnection(): Boolean {
        try {
            val response = retrofitApi.getAllCamiones()
            if (response.isSuccessful) {
                MyAppConfig.setConnectionStatus(true) // Cambia el valor de isConect a true si la llamada es exitosa
                return true
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
        MyAppConfig.setConnectionStatus(false) // En caso de error, establece isConect en false
        return false
    }


    suspend fun getCamionById(id: Int): CamionItem? {
        /*
        try {
            val response = retrofitApi.getCamionById(id)
            if (response.isSuccessful) {
                return response.body()
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
         */
        // Si falla la llamada a la API, busca en la base de datos local
        MyAppConfig.setConnectionStatus(false)
        return localRepository.getCamionById(id)
    }

    suspend fun addCamion(camion: CamionItem): CamionItem? {
        /*
        try {
            val response = retrofitApi.addCamion(camion)
            if (response.isSuccessful) {
                return response.body()
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
         */
        // Si falla la llamada a la API, no se puede agregar el camión
        MyAppConfig.setConnectionStatus(false)
        localRepository.insert(camion)
        return null
    }

    suspend fun updateCamion(camion: CamionItem): CamionItem? {
        /*
        try {
            val response = retrofitApi.updateCamion(camion.ID, camion)
            if (response.isSuccessful) {
                return response.body()
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
         */
        // Si falla la llamada a la API, no se puede actualizar el camión
        MyAppConfig.setConnectionStatus(false)
        localRepository.update(camion)
        return null
    }

    suspend fun deleteCamionById(id: Int): Boolean {
        /*
        try {
            val response = retrofitApi.deleteCamion(id)
            if (response.isSuccessful) {
                return true
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
         */
        // Si falla la llamada a la API, no se puede eliminar el camión
        MyAppConfig.setConnectionStatus(false)
        localRepository.deleteCamionById(id)
        return false
    }
}
