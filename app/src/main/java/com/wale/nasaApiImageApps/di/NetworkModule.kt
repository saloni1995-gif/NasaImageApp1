package com.wale.nasaApiImageApps.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wale.nasaApiImageApps.data.local.AppDatabase
import com.wale.nasaApiImageApps.data.local.NasaImageDao
import com.wale.nasaApiImageApps.data.remote.ApiService
import com.wale.nasaApiImageApps.data.remote.NasaRemoteDataSource
import com.wale.nasaApiImageApps.data.repository.NasaImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/planetary/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideNasaService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideNasaRemoteDataSource(nasaService: ApiService) = NasaRemoteDataSource(nasaService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: NasaRemoteDataSource,
                          localDataSource: NasaImageDao) =
        NasaImageRepository(remoteDataSource, localDataSource)
}