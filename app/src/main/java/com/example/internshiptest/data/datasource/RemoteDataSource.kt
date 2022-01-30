package com.example.internshiptest.data.datasource

import com.example.internshiptest.data.network.ServerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val serverApi: ServerApi,
) {

    suspend fun getLatestNews() =
        withContext(Dispatchers.IO) {
            serverApi.getResponse().articles
        }
}