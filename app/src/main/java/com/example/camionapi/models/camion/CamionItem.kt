package com.example.camionapi.models.camion

data class CamionItem(
    val ID: Int,
    val color: String,
    val conductor: String,
    val dimension: String,
    val marca: String,
    val matricula: String,
    val modelo: String,
    val tipo: String,
    val yearOperative: Int
)