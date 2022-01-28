package com.example.internshiptest.di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.internshiptest.presentation.viewmodel.NewsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UIModule {

    @Binds
    fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsFragmentViewModel::class)
    fun bindNewsFragmentViewModel(viewModel: NewsFragmentViewModel): ViewModel
}