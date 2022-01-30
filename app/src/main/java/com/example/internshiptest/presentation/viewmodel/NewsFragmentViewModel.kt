package com.example.internshiptest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.usecase.GetLatestNewsUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    private val getLatestNewsUsecase: GetLatestNewsUsecase
) : ViewModel() {

    private val _news: MutableLiveData<List<Article>> = MutableLiveData()
    val news: LiveData<List<Article>> = _news

    init {
        getLatestNews()
    }

    private fun getLatestNews(){
        viewModelScope.launch {
            _news.value = getLatestNewsUsecase()
        }
    }

}