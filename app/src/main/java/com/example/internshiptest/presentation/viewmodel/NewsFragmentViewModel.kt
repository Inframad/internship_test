package com.example.internshiptest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.usecase.GetNewsUsecase
import com.example.internshiptest.domain.usecase.UpdateNewsUsecase
import com.example.internshiptest.presentation.state.NewsFragmentState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    private val getNewsUsecase: GetNewsUsecase,
    private val updateNewsUsecase: UpdateNewsUsecase
) : ViewModel() {

    private val _state: MutableLiveData<NewsFragmentState> = MutableLiveData()
    val state: LiveData<NewsFragmentState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = when (throwable) {
            is UnknownHostException -> NewsFragmentState.OFFLINE_MODE
            else -> NewsFragmentState.UNKNOWN_ERROR
        }
        Log.e("NewsFragment", throwable.toString())
    }

    init {
        updateNews()
    }

    fun updateNews() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = NewsFragmentState.LOADING
            val deferredNews = async { updateNewsUsecase() }
            deferredNews.await()
            _state.value = NewsFragmentState.LOADED
        }
    }

    fun getNews(): Flow<List<Article>> =
        getNewsUsecase()

}