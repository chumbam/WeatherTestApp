package com.temp.weathertestapp.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.temp.weathertestapp.R

object AppUtils {

    fun setWeatherIcon(context: Context, imageView: AppCompatImageView, weatherCode: Int) {
        when {
            weatherCode / 100 == 2 -> {
                Glide.with(context).load(R.drawable.ic_storm_weather).into(imageView)
            }
            weatherCode / 100 == 3 -> {
                Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
            }
            weatherCode / 100 == 5 -> {
                Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
            }
            weatherCode / 100 == 6 -> {
                Glide.with(context).load(R.drawable.ic_snow_weather).into(imageView)
            }
            weatherCode / 100 == 7 -> {
                Glide.with(context).load(R.drawable.ic_unknown).into(imageView)
            }
            weatherCode == 800 -> {
                Glide.with(context).load(R.drawable.ic_clear_day).into(imageView)
            }
            weatherCode == 801 -> {
                Glide.with(context).load(R.drawable.ic_few_clouds).into(imageView)
            }
            weatherCode == 803 -> {
                Glide.with(context).load(R.drawable.ic_broken_clouds).into(imageView)
            }
            weatherCode / 100 == 8 -> {
                Glide.with(context).load(R.drawable.ic_cloudy_weather).into(imageView)
            }
        }
    }
}