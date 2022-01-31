package com.example.internshiptest.domain.repository

import com.example.internshiptest.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getNews(): Flow<List<Article>>
    suspend fun updateNews()
}