package com.example.camionapi.repository

import androidx.annotation.WorkerThread
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionDao
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.models.camion.CamionRequest
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CamionRepository(private val camionDao: CamionDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCamionKardex: Flow<List<CamionItem>> = camionDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(Camion: CamionItem) {
        camionDao.insertAll(Camion)
    }
    @WorkerThread
    suspend fun getCamionById(id: Int): CamionItem? {
        return camionDao.getCamionById(id)
    }
    @WorkerThread
    suspend fun deleteCamionById(id: Int) {
        camionDao.deleteCamionById(id)
    }
    @WorkerThread
    suspend fun update(camion: CamionItem) {
        camionDao.updateCamion(camion)
    }

}