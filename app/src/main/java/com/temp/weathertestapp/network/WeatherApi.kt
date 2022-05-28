package com.temp.weathertestapp.network

import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.models.FiveDaysWeatherModel
import com.temp.weathertestapp.models.MultipleDaysWeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String
    ): Single<CurrentWeatherModel>

    @GET("forecast")
    fun getFiveDayWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String
    ): Single<FiveDaysWeatherModel>

    @GET("forecast/daily")
    fun getMultiDayWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("cnt") count: Int,
        @Query("appid") appId: String
    ): Single<MultipleDaysWeatherModel>
}