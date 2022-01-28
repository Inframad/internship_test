package com.example.internshiptest.di

import android.content.Context
import com.example.internshiptest.MainActivity
import com.example.internshiptest.di.data.network.NetworkModule
import com.example.internshiptest.di.domain.DomainModule
import com.example.internshiptest.di.ui.UIModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
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
}