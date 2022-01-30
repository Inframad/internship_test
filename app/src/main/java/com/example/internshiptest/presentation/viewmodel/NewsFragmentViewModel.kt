package com.example.internshiptest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.usecase.GetLatestNewsUsecase
import com.example.internshiptest.presentation.state.NewsFragmentState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    private val getLatestNewsUsecase: GetLatestNewsUsecase
) : ViewModel() {

    private val _state: MutableLiveData<NewsFragmentState> = MutableLiveData()
    val state: LiveData<NewsFragmentState> = _state

    private val _news: MutableLiveData<List<Article>> = MutableLiveData()
    val news: LiveData<List<Article>> = _news

    fun getLatestNews() {
        viewModelScope.launch {
            _state.value = NewsFragmentState.LOADING
            val deferredNews = async { getLatestNewsUsecase() }
            _news.value = deferredNews.await()
            _state.value = NewsFragmentState.LOADED
        }
    }

}