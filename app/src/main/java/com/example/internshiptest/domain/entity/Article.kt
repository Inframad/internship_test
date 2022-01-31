package com.example.internshiptest.domain.entity

import java.time.OffsetDateTime

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val date: OffsetDateTime,
    val imageUrl: String,
    val author: String
)