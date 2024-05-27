package com.example.camionapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitObject {
    val camionesApi = Retrofit.Builder()
        .baseUrl("https://invcamionapi-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CamionApi :: class.java)
}