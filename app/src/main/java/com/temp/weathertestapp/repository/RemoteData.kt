package com.temp.weathertestapp.repository

import com.temp.weathertestapp.network.WeatherApi
import javax.inject.Inject

class RemoteData @Inject constructor(private val api: WeatherApi) {

    fun getCurrentWeather(cityName: String, units: String, lang: String, appId: String) =
        api.getCurrentWeather(cityName, units, lang, appId)

    fun getFiveDaysWeather(cityName: String, units: String, lang: String, appId: String) =
        api.getFiveDayWeather(cityName, units, lang, appId)

    fun getMultiDayWeather(
        cityName: String,
        units: String,
        lang: String,
        dayCount: Int,
        appId: String
    ) = api.getMultiDayWeather(cityName, units, lang, dayCount, appId)

}