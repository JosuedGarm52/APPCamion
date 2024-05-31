package com.example.camionapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitObject {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://invcamionapi-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val camionesApi = retrofit.create(CamionApi::class.java)
    val loginApi = retrofit.create(Login::class.java)
}