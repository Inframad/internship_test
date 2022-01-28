package com.example.internshiptest.domain.usecase

import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestNewsUsecase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<Article>> =
        repository.getLatestNews()

}