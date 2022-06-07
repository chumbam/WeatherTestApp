package com.temp.weathertestapp.utils

import com.temp.weathertestapp.models.CurrentWeatherModel

sealed class WeatherAppState {
    object Default : WeatherAppState()
    object Loading : WeatherAppState()
    object CurrentWeatherListScreenState: WeatherAppState()
    data class MoreWeatherScreenState(val data: CurrentWeatherModel): WeatherAppState()
    data class Error(val message: String) : WeatherAppState()
}
