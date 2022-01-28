package com.example.internshiptest.di.domain

import com.example.internshiptest.data.repository.RepositoryImpl
import com.example.internshiptest.domain.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}