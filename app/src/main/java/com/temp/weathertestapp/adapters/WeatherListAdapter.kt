package com.temp.weathertestapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.temp.weathertestapp.R
import com.temp.weathertestapp.databinding.RvWeatherCellBinding
import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.utils.AppUtils
import com.temp.weathertestapp.utils.MyDiffUtils
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

        var oldWeatherList = emptyList<CurrentWeatherModel>()
        private set
//    private val differCallback = object : DiffUtil.ItemCallback<CurrentWeatherModel>() {
//        override fun areItemsTheSame(
//            oldItem: CurrentWeatherModel,
//            newItem: CurrentWeatherModel
//        ): Boolean {
//            return oldItem.name == newItem.name
//        }
//
//        override fun areContentsTheSame(
//            oldItem: CurrentWeatherModel,
//            newItem: CurrentWeatherModel
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }

//    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val binding =
            RvWeatherCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return oldWeatherList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        val weatherItem = oldWeatherList[position]
        holder.bind(weatherItem)
//        holder.itemView.setOnClickListener {
//            onItemClickListener?.let {
//                it(weatherItem) }
//        }

    }

    inner class WeatherListViewHolder(private val itemBinding: RvWeatherCellBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(weatherItem: CurrentWeatherModel) {
            itemBinding.apply {
                rvCityName.text = weatherItem.name
                rvDegrees.text = weatherItem.main.temp.toInt().toString() + "°C"
                rvWeekDay.text = getWeekDay()
                rvWeatherDescription.text = weatherItem.weather.first().main
                rvFeelLike.text = weatherItem.main.feelLike.toInt().toString() + "°C"
                rvHumidityValue.text = weatherItem.main.humidity.toInt().toString() + "%"
                rvWindSpeed.text = weatherItem.wind.speed.toInt().toString() + " м/с"
                AppUtils.setWeatherIcon(
                    itemView.context,
                    rvWeatherIcon,
                    weatherItem.weather.first().id
                )
                rvCard.setOnClickListener {
                    onItemClickListener?.let {
                        it(weatherItem) }
                }
            }
        }
    }


//    fun setData(newWeatherList: List<CurrentWeatherModel>){
//        val diffUtils = MyDiffUtils(oldWeatherList, newWeatherList)
//        val diffResult = DiffUtil.calculateDiff(diffUtils)
//        oldWeatherList = newWeatherList
//        diffResult.dispatchUpdatesTo(this)
//    }
    fun setData(newWeatherList: List<CurrentWeatherModel>){
        oldWeatherList = newWeatherList
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((CurrentWeatherModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (CurrentWeatherModel) -> Unit) {
        onItemClickListener = listener
    }

    @SuppressLint("SimpleDateFormat")
    fun getWeekDay(): String {
        val sdf = SimpleDateFormat("EEEE")
        val date = Date()
        return (sdf.format(date))
    }
}