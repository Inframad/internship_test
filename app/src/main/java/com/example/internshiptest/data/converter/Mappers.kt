package com.example.internshiptest.data.converter

import com.example.internshiptest.data.model.ArticleDTO
import com.example.internshiptest.domain.entity.Article
import java.time.OffsetDateTime

fun ArticleDTO.toArticle() =
    Article(
        title = title,
        description = description,
        date = OffsetDateTime.parse(publishedAt),
        author = author
    )