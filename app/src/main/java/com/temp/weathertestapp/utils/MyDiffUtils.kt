package com.temp.weathertestapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.temp.weathertestapp.models.CurrentWeatherModel

class MyDiffUtils(
    private val oldList: List<CurrentWeatherModel>,
    private val newList: List<CurrentWeatherModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return when {
           oldList.size != newList.size -> false
           oldList[oldItemPosition].name != newList[newItemPosition].name -> false
           oldList[oldItemPosition].coord != newList[newItemPosition].coord -> false
           else -> true
       }
    }
}