package com.temp.weathertestapp.repository

import com.temp.weathertestapp.db.WeatherDAO
import com.temp.weathertestapp.models.CurrentWeatherModel
import javax.inject.Inject

class LocalData @Inject constructor(private val weatherDAO: WeatherDAO) {

    suspend fun addCityToDb(city: CurrentWeatherModel) = weatherDAO.addCityToDb(city)
    fun getAllCity() = weatherDAO.getAllCity()
    suspend fun deleteCityFromDb(city: CurrentWeatherModel) = weatherDAO.deleteCity(city)
}