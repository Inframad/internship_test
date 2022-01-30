package com.example.internshiptest.data.datasource.local

import android.content.Context
import com.example.internshiptest.data.model.ArticleDb
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class LocalDataSource
@Inject constructor(
    private val newsDao: NewsDao,
    private val context: Context
) {

    fun getNews() =
        newsDao.getAll()

    suspend fun deleteNews() {
        withContext(Dispatchers.IO) {
            newsDao.deleteAll()
            val cache = File(context.cacheDir, "picasso-cache")
            if (cache.exists() && cache.isDirectory) {
                cache.deleteRecursively()
            }
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