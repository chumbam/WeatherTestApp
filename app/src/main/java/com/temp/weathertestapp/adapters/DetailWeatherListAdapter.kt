package com.temp.weathertestapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.temp.weathertestapp.databinding.RvWeeklyWeatherCellBinding
import com.temp.weathertestapp.models.Daily
import com.temp.weathertestapp.utils.AppUtils
import java.util.*

class DetailWeatherListAdapter :
    RecyclerView.Adapter<DetailWeatherListAdapter.DetailWeatherListViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Daily>() {
        override fun areItemsTheSame(
            oldItem: Daily,
            newItem: Daily
        ): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(
            oldItem: Daily,
            newItem: Daily
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailWeatherListAdapter.DetailWeatherListViewHolder {
        val binding = RvWeeklyWeatherCellBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return DetailWeatherListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class DetailWeatherListViewHolder(private val itemBinding: RvWeeklyWeatherCellBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(weatherItem: Daily) {
            val calendar = Calendar.getInstance(TimeZone.getDefault()).also {
                it.timeInMillis = weatherItem.dt * 1000L
            }

            itemBinding.apply {
                AppUtils.setWeatherIcon(
                    itemView.context,
                    rvWeeklyWeatherIcon,
                    weatherItem.weather.first().id
                )
                rvWeeklyTemp.text = weatherItem.temp.day.toInt().toString() + "°C/" +
                        weatherItem.temp.night.toInt().toString() + "°C"
                rvWeeklyWeatherDescription.text = weatherItem.weather.first().main
                rvWeeklyDay.text = AppUtils.getWeekDay(weatherItem.dt)
                Log.e("Adapter", weatherItem.dt.toString())
            }
        }
    }

    override fun onBindViewHolder(holder: DetailWeatherListViewHolder, position: Int) {
        val weatherItem = differ.currentList[position]
        holder.bind(weatherItem)
    }

//    private var onItemClickListener: ((CurrentWeatherModel) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (CurrentWeatherModel) -> Unit) {
//        onItemClickListener = listener
//    }
}