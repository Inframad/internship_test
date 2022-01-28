package com.example.internshiptest.di.data.network

import com.example.internshiptest.data.network.ServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()

    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideFocusStartLoanApi(
        retrofit: Retrofit,
    ): ServerApi =
        retrofit.create(ServerApi::class.java)
}