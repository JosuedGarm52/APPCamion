package com.example.camionapi.models.random

data class Cuenta (
    val usuario: String,
    val password: String
)
data class Resultt(
    val message : String,
    val token : String = "",
    val usuario : String = ""
)