package com.example.internshiptest.domain.entity

import java.time.OffsetDateTime

data class Article(
    private val title: String,
    private val description: String,
    private val date: OffsetDateTime,
    private val author: String
)