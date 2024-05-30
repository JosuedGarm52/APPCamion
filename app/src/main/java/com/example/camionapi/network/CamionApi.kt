package com.example.camionapi.network

import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path

interface CamionApi {
    @GET("camiones")
    suspend fun getAllCamiones(): Response<List<CamionItem>>

    @GET("camion/{id}")
    suspend fun getCamionById(@Path("id") id: Int): Response<CamionItem>

    @POST("camion")
    suspend fun addCamion(@Body camion: CamionRequest): Response<CamionRequest>

    @PUT("camion/{id}")
    suspend fun updateCamion(@Path("id") id: Int, @Body camion: CamionRequest): Response<CamionRequest>

    @DELETE("camion/{id}")
    suspend fun deleteCamion(@Path("id") id: Int): Response<Void>
}
