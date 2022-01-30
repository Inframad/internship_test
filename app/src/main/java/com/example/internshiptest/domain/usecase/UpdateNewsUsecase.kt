package com.example.internshiptest.domain.usecase

import com.example.internshiptest.domain.repository.Repository
import javax.inject.Inject

class UpdateNewsUsecase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): Boolean =
        repository.updateNews()

}