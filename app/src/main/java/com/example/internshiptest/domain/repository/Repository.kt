package com.example.internshiptest.domain.repository

import com.example.internshiptest.domain.entity.Article

interface Repository {

    suspend fun getLatestNews(): List<Article>
}