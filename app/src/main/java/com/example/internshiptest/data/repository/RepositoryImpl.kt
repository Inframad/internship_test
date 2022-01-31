package com.example.internshiptest.data.repository

import com.example.internshiptest.data.converter.toArticle
import com.example.internshiptest.data.converter.toArticleDb
import com.example.internshiptest.data.datasource.RemoteDataSource
import com.example.internshiptest.data.datasource.local.LocalDataSource
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override fun getNews(): Flow<List<Article>> =
        localDataSource.getNews().map {
            it.map { articleDb -> articleDb.toArticle() }
        }

    override suspend fun updateNews() {
        val news = remoteDataSource.getLatestNews().map { articleDTO ->
            articleDTO.toArticleDb()
        }
        localDataSource.deleteNews()
        localDataSource.saveNews(news)
    }

}