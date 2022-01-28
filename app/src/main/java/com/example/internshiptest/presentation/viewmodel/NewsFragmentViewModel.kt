package com.example.internshiptest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.usecase.GetLatestNewsUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    private val getLatestNewsUsecase: GetLatestNewsUsecase
) : ViewModel() {

    suspend fun getLatestNews(): Flow<List<Article>> =
        getLatestNewsUsecase()
}