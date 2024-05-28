package com.example.camionapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.camionapi.models.camion.CamionDao
import com.example.camionapi.models.camion.CamionItem

@Database(entities = [CamionItem::class], version = 1)
abstract class CamionDatabase : RoomDatabase() {
    abstract fun camionDAO(): CamionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CamionDatabase? = null

        fun getDatabase(context: Context): CamionDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CamionDatabase::class.java,
                    "Camion_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}