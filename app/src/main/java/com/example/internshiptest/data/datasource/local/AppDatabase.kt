package com.example.internshiptest.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.internshiptest.data.model.ArticleDb

@Database(entities = [ArticleDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}