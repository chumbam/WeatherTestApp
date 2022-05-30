package com.temp.weathertestapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.temp.weathertestapp.models.*

class Converter {
    private val gson = Gson()

    //   Coord
    @TypeConverter
    fun fromCoordToString(coord: Coord): String {
        return gson.toJson(coord)
    }

    @TypeConverter
    fun fromStringToCoord(coord: String): Coord {
        return gson.fromJson(coord, Coord::class.java)
    }

    //  WeatherItems
    @TypeConverter
    fun fromWeatherItemsToString(list: List<WeatherItem>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToWeatherItems(list: String): List<WeatherItem> {
        val type = object : TypeToken<List<WeatherItem>>() {}.type
        return gson.fromJson<List<WeatherItem>>(list, type)
    }

    //    Main
    @TypeConverter
    fun fromMainToString(mainWeather: Main): String {
        return gson.toJson(mainWeather)
    }

    @TypeConverter
    fun fromStringToMain(mainWeather: String): Main {
        return gson.fromJson(mainWeather, Main::class.java)
    }

    //    Clouds
    @TypeConverter
    fun fromCloudsToSting(clouds: Clouds): String {
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun fromStringToClouds(clouds: String): Clouds {
        return gson.fromJson(clouds, Clouds::class.java)
    }

    //    Sys
    @TypeConverter
    fun fromSysToSting(sys: Sys): String {
        return gson.toJson(sys)
    }

    @TypeConverter
    fun fromStringToSys(sys: String): Sys {
        return gson.fromJson(sys, Sys::class.java)
    }

    //    Wind
    @TypeConverter
    fun fromWindToSting(wind: Wind): String {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun fromStringToWind(wind: String): Wind {
        return gson.fromJson(wind, Wind::class.java)
    }

}