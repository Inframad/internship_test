package com.example.internshiptest.di

import android.content.Context
import com.example.internshiptest.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
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