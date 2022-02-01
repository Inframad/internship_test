package com.example.internshiptest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internshiptest.domain.entity.Article
import com.example.internshiptest.domain.entity.RequestError
import com.example.internshiptest.domain.usecase.GetNewsUsecase
import com.example.internshiptest.domain.usecase.UpdateNewsUsecase
import com.example.internshiptest.presentation.state.NewsFragmentState
import com.example.internshiptest.presentation.state.NewsFragmentState.*
import com.example.internshiptest.presentation.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class NewsFragmentViewModel
@Inject constructor(
    getNewsUsecase: GetNewsUsecase,
    private val updateNewsUsecase: UpdateNewsUsecase
) : ViewModel() {

    private val _state: SingleLiveEvent<NewsFragmentState> = SingleLiveEvent()
    val state: LiveData<NewsFragmentState> = _state

    val news: Flow<List<Article>> = getNewsUsecase().onEach {
        if (it.isEmpty()) _state.value = NOTHING_TO_SHOW
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = when (throwable) {
            is UnknownHostException -> OFFLINE_MODE
            is RequestError -> when (throwable.code) {
                500 -> SERVER_IS_NOT_AVAILABLE
                else -> UNKNOWN_ERROR
            }
            else -> UNKNOWN_ERROR
        }
    }

    init {
        updateNews()
    }

    fun updateNews() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = LOADING
            updateNewsUsecase()
            _state.value = LOADED
        }
    }

}