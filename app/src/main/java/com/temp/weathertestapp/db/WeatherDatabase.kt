package com.temp.weathertestapp.db

import androidx.room.*
import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.utils.Converter

@Database(
    entities = [CurrentWeatherModel::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDAO
}