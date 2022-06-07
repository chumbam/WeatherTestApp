//package com.temp.weathertestapp.models
//
//import com.google.gson.annotations.SerializedName
//
//data class FiveDaysWeatherModel(
//    @SerializedName("city")
//    var city: City,
//
//    @SerializedName("cnt")
//    val cnt: Int,
//
//    @SerializedName("cod")
//    val cod: String,
//
//    @SerializedName("message")
//    val message: Double,
//
//    @SerializedName("list")
//    val list: List<ItemHourly>
//
//)
//
//data class City(
//    @SerializedName("country")
//    var country: String,
//
//    @SerializedName("coord")
//    val coord: Coord,
//
//    @SerializedName("name")
//    val name: String,
//
//    @SerializedName("id")
//    val id: Int,
//
//    @SerializedName("population")
//    val population: Int
//)
//
//data class ItemHourly(
//    @SerializedName("dt")
//    var dt: Int,
//
//    @SerializedName("dt_txt")
//    val dtTxt: String,
//
//    @SerializedName("weather")
//    val weather: List<WeatherItem>,
//
//    @SerializedName("main")
//    val main: Main,
//
//    @SerializedName("clouds")
//    val clouds: Clouds,
//
//    @SerializedName("sys")
//    val sys: Sys,
//
//    @SerializedName("wind")
//    val wind: Wind,
//
//    @SerializedName("rain")
//    val rain: Rain
//
//)
//
//data class Rain(
//    @SerializedName("3h")
//    var jsonMember3h: Double
//)
