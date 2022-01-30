package com.example.internshiptest.data.network

import com.example.internshiptest.data.model.ResponseDTO
import retrofit2.http.GET

interface ServerApi {

    //@GET("/v2/top-headlines?country=ru&apiKey=422e843b90164b5ca2fc1954e5eee5bc")
    @GET("v3/377b942b-9dab-4bab-83ad-db70147925f0")
    suspend fun getLatestNews(): ResponseDTO
}