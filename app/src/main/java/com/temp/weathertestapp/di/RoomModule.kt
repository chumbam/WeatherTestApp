package com.temp.weathertestapp.di

import android.content.Context
import androidx.room.Room
import com.temp.weathertestapp.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun getWeatherDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db.db").build()

    @Provides
    @Singleton
    fun getWeatherDao(db: WeatherDatabase) = db.getWeatherDao()
}
