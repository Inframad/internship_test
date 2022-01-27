package com.example.internshiptest.data.repository

import com.example.internshiptest.data.converter.toArticle
import com.example.internshiptest.data.datasource.RemoteDataSource
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getLatestNews(): Flow<List<Article>> =
            remoteDataSource.getLatestNews().map {
                it.map { articleDTO -> articleDTO.toArticle()}
            }
}