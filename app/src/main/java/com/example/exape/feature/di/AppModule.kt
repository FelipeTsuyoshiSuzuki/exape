package com.example.exape.feature.di

import com.example.exape.feature.list.remote.RickAndMortyRepository
import com.example.exape.feature.list.remote.RickAndMotyApi
import com.example.exape.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApi() : RickAndMotyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        api: RickAndMotyApi
    ) = RickAndMortyRepository(api)
}