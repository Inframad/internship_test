package com.example.internshiptest.data.converter

import com.example.internshiptest.data.model.ArticleDTO
import com.example.internshiptest.data.model.ArticleDb
import com.example.internshiptest.domain.entity.Article
import java.time.OffsetDateTime

fun ArticleDTO.toArticleDb() =
    ArticleDb(
        title = title,
        description = description,
        publishedAt = publishedAt,
        urlToImage = urlToImage,
        url = url,
        author = author,
        content = content
    )

fun ArticleDb.toArticle() =
    Article(
        id = id,
        title = title ?: "",
        description = description ?: "",
        date = OffsetDateTime.parse(publishedAt),
        imageUrl = urlToImage,
        author = author ?: ""
    )