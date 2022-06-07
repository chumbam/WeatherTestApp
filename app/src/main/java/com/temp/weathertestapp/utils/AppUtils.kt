package com.temp.weathertestapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.temp.weathertestapp.R
import java.text.SimpleDateFormat
import java.util.*

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

//    fun getTime(calendar: Calendar, context: Context): String? {
//        val hour = calendar[Calendar.HOUR_OF_DAY]
//        val minute = calendar[Calendar.MINUTE]
//        val hourString: String = if (hour < 10) {
//            String.format(Locale.getDefault(), context.getString(R.string.zero_label), hour)
//        } else {
//            String.format(Locale.getDefault(), "%d", hour)
//        }
//        val minuteString: String = if (minute < 10) {
//            String.format(Locale.getDefault(), context.getString(R.string.zero_label), minute)
//        } else {
//            String.format(Locale.getDefault(), "%d", minute)
//        }
//        return "$hourString:$minuteString"
//    }

    @SuppressLint("SimpleDateFormat")
    fun getWeekDay(timeInMillis: Int): String{
        val frm = SimpleDateFormat("EEEE")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis * 1000L
        Log.e("AppUtils", frm.format(calendar.time))
        return frm.format(calendar.time)
    }
}