package com.example.internshiptest.domain.entity

import java.time.OffsetDateTime

data class Article(
    val title: String,
    val description: String,
    val date: OffsetDateTime,
    val imageUrl: String,
    val author: String
)