package com.example.camionapi.application

import android.app.Application
import com.example.camionapi.database.CamionDatabase
import com.example.camionapi.repository.CamionRepository
import com.example.camionapi.repository.CombinedCamionRepository

class CamionApplication : Application() {
    //val retrofitApi = RetroFitObject.camionesApi
    val database by lazy { CamionDatabase.getDatabase(this) }
    val localRepository by lazy { CamionRepository(database.camionDAO()) }
    val combinedRepository by lazy { CombinedCamionRepository(
        //retrofitApi,
        localRepository) }
}
