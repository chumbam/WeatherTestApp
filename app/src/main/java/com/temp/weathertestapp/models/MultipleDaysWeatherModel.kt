package com.temp.weathertestapp.models

import com.google.gson.annotations.SerializedName

data class MultipleDaysWeatherModel(
    @SerializedName("city")
    var city: City,

    @SerializedName("cnt")
    val cnt: Int,

    @SerializedName("cod")
    val cod: String,

    @SerializedName("message")
    val message: Double,

    @SerializedName("list")
    val list: List<ItemList>
)

data class ItemList(
    @SerializedName("dt")
    var dt: Int,

    @SerializedName("temp")
    val temp: Temp,

    @SerializedName("deg")
    val deg: Int,

    @SerializedName("weather")
    val weather: List<WeatherItem>,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Double,

    @SerializedName("clouds")
    val clouds: Int,

    @SerializedName("speed")
    val speed: Double,

    @SerializedName("rain")
    val rain: Double
)

data class Temp(
    @SerializedName("min")
    var min: Double,

    @SerializedName("max")
    val max: Double,

    @SerializedName("eve")
    val eve: Double,

    @SerializedName("night")
    val night: Double,

    @SerializedName("day")
    val day: Double,

    @SerializedName("morn")
    var morn: Double
)