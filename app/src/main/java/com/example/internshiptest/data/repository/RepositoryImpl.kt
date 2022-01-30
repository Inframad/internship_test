package com.example.internshiptest.data.repository

import com.example.internshiptest.data.converter.toArticle
import com.example.internshiptest.data.datasource.RemoteDataSource
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getLatestNews(): List<Article> =
        remoteDataSource.getLatestNews().map { articleDTO ->
            articleDTO.toArticle()
        }
}