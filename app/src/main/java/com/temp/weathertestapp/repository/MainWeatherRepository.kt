package com.temp.weathertestapp.repository


import javax.inject.Inject

class MainWeatherRepository @Inject constructor(
    private val localData: LocalData,
    private val remoteData: RemoteData
) {
    //Remote
    fun getCurrentWeather(cityName: String, units: String, lang: String, appId: String) =
        remoteData.getCurrentWeather(cityName, units, lang, appId)

    fun getFiveDayWeather(cityName: String, units: String, lang: String, appId: String) =
        remoteData.getFiveDaysWeather(cityName, units, lang, appId)

    fun getMultiWeather(
        cityName: String,
        units: String,
        lang: String,
        dayCount: Int,
        appId: String
    ) = remoteData.getMultiDayWeather(cityName, units, lang, dayCount, appId)

    //Local

}