package com.example.camionapi.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.camionapi.models.camion.CamionDao
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.models.camion.CamionRequest
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
    var isConectado : Boolean = false

    // Método para obtener todos los camiones, primero intenta obtenerlos de la API,
    // si falla, los obtiene de la base de datos local
    suspend fun getAllCamiones(): Flow<List<CamionItem>> {

        try {
            val response = retrofitApi.getAllCamiones()
            if (response.isSuccessful) {
                return response.body()?.let { flowOf(it) } ?: flowOf(emptyList())
            } else {
                Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
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
                isConectado = true
                return true
            }
        } catch (e: Exception) {
            Log.e("API", "Error fetching data from API: ${e.message}")
        }
        MyAppConfig.setConnectionStatus(false) // En caso de error, establece isConect en false
        isConectado = false
        return false
    }


    suspend fun getCamionById(id: Int): CamionItem? {
        if(isConectado){
            try {
                val response = retrofitApi.getCamionById(id)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            isConectado = false
            MyAppConfig.setConnectionStatus(false)
            return null
        }else{
            try {
                val response = retrofitApi.getCamionById(id)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }

            // Si falla la llamada a la API, busca en la base de datos local
            isConectado = false
            MyAppConfig.setConnectionStatus(false)
            return localRepository.getCamionById(id)
        }
    }

    suspend fun addCamion(camion: CamionRequest): CamionRequest? {
        if(isConectado){
            try {
                val response = retrofitApi.addCamion(camion)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            isConectado = false
            MyAppConfig.setConnectionStatus(false)
            return null
        }else{
            try {
                val response = retrofitApi.addCamion(camion)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                }else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            // Si falla la llamada a la API, no se puede agregar el camión
            val camionItem = CamionItem(
                ID = camion.ID,
                color = camion.color,
                conductor = camion.conductor,
                dimension = camion.dimension,
                marca = camion.marca,
                matricula = camion.matricula,
                modelo = camion.modelo,
                tipo = camion.tipo,
                yearOperative = camion.operativo
            )
            MyAppConfig.setConnectionStatus(false)
            localRepository.insert(camionItem)
            return null
        }
    }

    suspend fun updateCamion(camion: CamionRequest): CamionRequest? {

        if(isConectado){
            try {
                val response = retrofitApi.updateCamion(camion.ID, camion)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            isConectado = false
            MyAppConfig.setConnectionStatus(false)
            return null
        }else{
            try {
                val response = retrofitApi.updateCamion(camion.ID, camion)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return response.body()
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            // Si falla la llamada a la API, no se puede actualizar el camión
            val camionItem = CamionItem(
                ID = camion.ID,
                color = camion.color,
                conductor = camion.conductor,
                dimension = camion.dimension,
                marca = camion.marca,
                matricula = camion.matricula,
                modelo = camion.modelo,
                tipo = camion.tipo,
                yearOperative = camion.operativo
            )
            MyAppConfig.setConnectionStatus(false)
            localRepository.update(camionItem)
            return null
        }
    }

    suspend fun deleteCamionById(id: Int): Boolean {
        if(isConectado){
            try {
                val response = retrofitApi.deleteCamion(id)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return true
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            isConectado = false
            MyAppConfig.setConnectionStatus(false)
            return false
        }else{
            try {
                val response = retrofitApi.deleteCamion(id)
                if (response.isSuccessful) {
                    isConectado = true
                    MyAppConfig.setConnectionStatus(true)
                    return true
                } else {
                    Log.e("API", "Response not successful: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching data from API: ${e.message}")
            }
            // Si falla la llamada a la API, no se puede eliminar el camión
            MyAppConfig.setConnectionStatus(false)
            localRepository.deleteCamionById(id)
            return false
        }
    }
}
