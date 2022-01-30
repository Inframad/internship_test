package com.example.internshiptest.data.converter

import com.example.internshiptest.data.model.ArticleDTO
import com.example.internshiptest.domain.entity.Article
import java.time.OffsetDateTime

fun ArticleDTO.toArticle() =
    Article(
        title = title ?: "No title",
        description = description ?: "No description",
        date = OffsetDateTime.parse(publishedAt),
        imageUrl = urlToImage ?: "", //TODO
        author = author ?: "Unknown"
    )