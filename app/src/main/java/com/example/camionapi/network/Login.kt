package com.example.camionapi.network

import com.example.camionapi.models.random.Cuenta
import com.example.camionapi.models.random.Resultt
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Login {
    @POST("api/auth/login")
    suspend fun login(@Body cuenta: Cuenta): Response<Resultt>

    @POST("api/auth/verifyToken")
    suspend fun verify(@Header("Authorization") token: String): Response<Resultt>
}