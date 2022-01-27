package com.example.internshiptest.data.model

data class ResponseDTO(
    private val status: String,
    private val totalResults: Int,
    private val articles: List<ArticleDTO>
)