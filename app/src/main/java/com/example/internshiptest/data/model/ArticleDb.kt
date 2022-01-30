package com.example.internshiptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
