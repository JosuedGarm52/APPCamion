package com.example.camionapi.application

import android.app.Application
import com.example.camionapi.database.CamionDatabase
import com.example.camionapi.repository.CamionRepository

class CamionApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CamionDatabase.getDatabase(this) }
    val repository by lazy { CamionRepository(database.camionDAO()) }
}