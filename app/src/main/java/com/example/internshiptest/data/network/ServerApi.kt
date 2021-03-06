package com.example.internshiptest.data.network

import com.example.internshiptest.data.model.ResponseDTO
import retrofit2.http.GET

interface ServerApi {

    @GET("/v2/top-headlines?country=ru&apiKey=422e843b90164b5ca2fc1954e5eee5bc")
    suspend fun getResponse(): ResponseDTO
}