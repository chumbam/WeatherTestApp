package com.temp.weathertestapp.repository

import com.temp.weathertestapp.network.WeatherApi
import javax.inject.Inject

class RemoteData @Inject constructor(private val api: WeatherApi) {

    fun getCurrentWeather(cityName: String, units: String, lang: String, appId: String) =
        api.getCurrentWeather(cityName, units, lang, appId)

//    fun getFiveDaysWeather(cityName: String, units: String, lang: String, appId: String) =
//        api.getFiveDayWeather(cityName, units, lang, appId)

    fun getWeeklyWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String,
        lang: String,
        appId: String
    ) = api.getWeeklyWeather(lat, lon, exclude, units, lang, appId)

}