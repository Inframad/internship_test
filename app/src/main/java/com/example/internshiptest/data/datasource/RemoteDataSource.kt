package com.example.internshiptest.data.datasource

import com.example.internshiptest.data.network.ServerApi
import com.example.internshiptest.domain.entity.RequestError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val serverApi: ServerApi,
) {

    suspend fun getLatestNews() =
        try {
            withContext(Dispatchers.IO) {
                serverApi.getResponse().articles
            }
        } catch (httpException: retrofit2.HttpException) {
            throw RequestError(httpException.code())
        }
}