package com.example.camionapi.models.camion

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Camion")
data class CamionItem(
    @PrimaryKey val ID: Int,
    val color: String,
    val conductor: String,
    val dimension: String,
    val marca: String,
    val matricula: String,
    val modelo: String,
    val tipo: String,
    val yearOperative: Int
)