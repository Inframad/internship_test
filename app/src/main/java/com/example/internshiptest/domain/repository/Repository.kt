package com.example.internshiptest.domain.repository

import com.example.internshiptest.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getLatestNews(): Flow<List<Article>>
}