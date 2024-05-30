package com.example.camionapi.models.camion

data class CamionRequest(
    val ID: Int = 0,
    val color: String,
    val matricula: String,
    val conductor: String,
    val operativo: Int,
    val marca: String,
    val modelo: String,
    val dimension: String,
    val tipo: String
)