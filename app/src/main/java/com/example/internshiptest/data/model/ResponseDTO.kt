package com.example.internshiptest.data.model

data class ResponseDTO(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDTO>
)