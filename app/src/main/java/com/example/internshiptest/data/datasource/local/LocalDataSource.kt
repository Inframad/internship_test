package com.example.internshiptest.data.datasource.local

import com.example.internshiptest.data.model.ArticleDb
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource
@Inject constructor(
    private val newsDao: NewsDao
) {

    fun getNews() =
        newsDao.getAll()

    suspend fun deleteNews() {
        withContext(Dispatchers.IO) {
            newsDao.deleteAll()
        }
    }

    suspend fun saveNews(news: List<ArticleDb>) {
        withContext(Dispatchers.IO) {
            news.forEach {
                Picasso.get().load(it.urlToImage).fetch()
            }
            newsDao.insertAll(news)
        }
    }
}