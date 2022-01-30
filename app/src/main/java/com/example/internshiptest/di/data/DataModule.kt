package com.example.internshiptest.di.data

import android.content.Context
import androidx.room.Room
import com.example.internshiptest.data.datasource.local.AppDatabase
import com.example.internshiptest.data.datasource.local.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLoanDao(database: AppDatabase): NewsDao =
        database.newsDao()

}