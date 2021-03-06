package com.example.internshiptest.di

import android.content.Context
import com.example.internshiptest.di.data.DataModule
import com.example.internshiptest.di.data.network.NetworkModule
import com.example.internshiptest.di.domain.DomainModule
import com.example.internshiptest.di.ui.UIModule
import com.example.internshiptest.ui.MainActivity
import com.example.internshiptest.ui.NewsDetailFragment
import com.example.internshiptest.ui.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        DataModule::class,
        NetworkModule::class,
        UIModule::class
    ]
)
interface AppComponent {

    @Singleton
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: NewsDetailFragment)
}