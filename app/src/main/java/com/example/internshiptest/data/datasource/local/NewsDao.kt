package com.example.internshiptest.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.internshiptest.data.model.ArticleDb
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("select * FROM articledb")
    fun getAll(): Flow<List<ArticleDb>>

    @Query("DELETE FROM articledb")
    fun deleteAll()

    @Insert
    suspend fun insertAll(news: List<ArticleDb>)
}