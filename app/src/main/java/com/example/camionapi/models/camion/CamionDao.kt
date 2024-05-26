package com.example.camionapi.models.camion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CamionDao {
    @Query("SELECT * FROM Camion")
    fun getAll(): Flow<List<CamionItem>>

    @Query("SELECT * FROM Camion WHERE ID = :id")
    suspend fun getCamionById(id: Int): CamionItem?

    @Query("DELETE FROM Camion WHERE ID = :id")
    suspend fun deleteCamionById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg camiones: CamionItem)

    @Update
    suspend fun updateCamion(camion: CamionItem)
}
