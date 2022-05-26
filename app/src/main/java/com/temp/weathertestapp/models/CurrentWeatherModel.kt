package com.temp.weathertestapp.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(
    @SerializedName("dt")
    val dt: Int,

    @SerializedName("coord")
    val coord: Coord,

    @SerializedName("weather")
    val weather: List<WeatherItem>,

    @SerializedName("name")
    val name: String,

    @SerializedName("cod")
    val cod: Int,

    @SerializedName("main")
    val main: Main,

    @SerializedName("clouds")
    val clouds: Clouds,

    @SerializedName("id")
    val id: Int,

    @SerializedName("sys")
    val sys: Sys,

    @SerializedName("base")
    val base: String,

    @SerializedName("wind")
    val wind: Wind
)

data class Coord(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)

data class WeatherItem(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("main")
    val main: String,

    @SerializedName("id")
    val id: Int
)

data class Main(
    @SerializedName("temp")
    var temp: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("grnd_level")
    val grndLevel: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Double,

    @SerializedName("sea_level")
    val seaLevel: Double,

    @SerializedName("temp_max")
    val tempMax: Double
)

data class Clouds(
    @SerializedName("all")
    var all: Int
)

data class Sys(
    @SerializedName("country")
    var country: String? = null,

    @SerializedName("sunrise")
    val sunrise: Int,

    @SerializedName("sunset")
    val sunset: Int,

    @SerializedName("message")
    val message: Double
)

data class Wind(
    @SerializedName("deg")
    var deg: Double,

    @SerializedName("speed")
    val speed: Double
)
