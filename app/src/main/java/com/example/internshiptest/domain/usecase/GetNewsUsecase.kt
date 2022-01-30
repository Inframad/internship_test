package com.example.internshiptest.domain.usecase

import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUsecase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<List<Article>> =
        repository.getNews()

}