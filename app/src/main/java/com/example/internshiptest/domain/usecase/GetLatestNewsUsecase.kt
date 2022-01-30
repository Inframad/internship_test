package com.example.internshiptest.domain.usecase

import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import javax.inject.Inject

class GetLatestNewsUsecase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<Article> =
        repository.getLatestNews()

}