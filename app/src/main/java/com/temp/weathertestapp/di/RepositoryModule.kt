package com.temp.weathertestapp.di

import com.temp.weathertestapp.network.WeatherApi
import com.temp.weathertestapp.repository.LocalData
import com.temp.weathertestapp.repository.MainWeatherRepository
import com.temp.weathertestapp.repository.RemoteData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(localData: LocalData, remoteData: RemoteData) =
        MainWeatherRepository(localData, remoteData)

    @Singleton
    @Provides
    fun provideLocalData() =
        LocalData()

    @Singleton
    @Provides
    fun provideRemoteData(weatherApi: WeatherApi) =
        RemoteData(weatherApi)
}