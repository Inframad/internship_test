package com.example.internshiptest

import android.app.Application
import com.example.internshiptest.di.AppComponent
import com.example.internshiptest.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}