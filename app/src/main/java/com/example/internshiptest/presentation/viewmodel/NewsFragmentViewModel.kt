package com.example.internshiptest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.usecase.GetNewsUsecase
import com.example.internshiptest.domain.usecase.UpdateNewsUsecase
import com.example.internshiptest.presentation.state.NewsFragmentState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    private val getNewsUsecase: GetNewsUsecase,
    private val updateNewsUsecase: UpdateNewsUsecase
) : ViewModel() {

    private val _state: MutableLiveData<NewsFragmentState> = MutableLiveData()
    val state: LiveData<NewsFragmentState> = _state

    init {
        //updateNews()
    }

    fun updateNews() {
        viewModelScope.launch {
            _state.value = NewsFragmentState.LOADING
            val deferredNews = async { updateNewsUsecase() }
            deferredNews.await()
            _state.value = NewsFragmentState.LOADED
        }
    }

    fun getNews(): Flow<List<Article>> =
        getNewsUsecase()

}