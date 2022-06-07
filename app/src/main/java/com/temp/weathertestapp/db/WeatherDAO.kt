package com.temp.weathertestapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.temp.weathertestapp.models.CurrentWeatherModel

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCityToDb(city: CurrentWeatherModel): Long

    @Query("SELECT * FROM city")
    fun getAllCity(): LiveData<List<CurrentWeatherModel>>

    @Delete
    suspend fun deleteCity(city: CurrentWeatherModel)
}