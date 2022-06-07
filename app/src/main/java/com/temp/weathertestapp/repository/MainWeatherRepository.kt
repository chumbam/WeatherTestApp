package com.temp.weathertestapp.repository


import com.temp.weathertestapp.models.CurrentWeatherModel
import javax.inject.Inject

class MainWeatherRepository @Inject constructor(
    private val localData: LocalData,
    private val remoteData: RemoteData
) {
    //Remote request
    fun getCurrentWeather(cityName: String, units: String, lang: String, appId: String) =
        remoteData.getCurrentWeather(cityName, units, lang, appId)

//    fun getFiveDayWeather(cityName: String, units: String, lang: String, appId: String) =
//        remoteData.getFiveDaysWeather(cityName, units, lang, appId)

    fun getWeeklyWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String,
        lang: String,
        appId: String
    ) = remoteData.getWeeklyWeather(lat, lon, exclude, units, lang, appId)

    //Local request
    fun getAllCity() = localData.getAllCity()
    suspend fun addCityToDb(city: CurrentWeatherModel) = localData.addCityToDb(city)
    suspend fun deleteCityFromDb(city: CurrentWeatherModel) = localData.deleteCityFromDb(city)

}