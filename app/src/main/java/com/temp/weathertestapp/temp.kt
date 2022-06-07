//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.AsyncListDiffer
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.temp.weathertestapp.databinding.RvWeatherCellBinding
//import com.temp.weathertestapp.models.CurrentWeatherModel
//import com.temp.weathertestapp.utils.AppUtils
//import java.text.SimpleDateFormat
//import java.util.*
//
//inner class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//private val differCallback = object : DiffUtil.ItemCallback<CurrentWeatherModel>() {
//    override fun areItemsTheSame(
//        oldItem: CurrentWeatherModel,
//        newItem: CurrentWeatherModel
//    ): Boolean {
//        return oldItem.coord == newItem.coord
//    }
//
//    override fun areContentsTheSame(
//        oldItem: CurrentWeatherModel,
//        newItem: CurrentWeatherModel
//    ): Boolean {
//        return oldItem == newItem
//    }
//}
//
//val differ = AsyncListDiffer(this, differCallback)
//
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
//    return WeatherListViewHolder(
//        LayoutInflater.from(parent.context)
//            .inflate(R.layout.rv_weather_cell, parent, false)
//    )
//}
//
//override fun getItemCount(): Int {
//    return differ.currentList.size
//}
//
//override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
//    val weatherItem = differ.currentList[position]
//    val data = Date()
//    holder.itemView.apply {
//        findViewById<TextView>(R.id.rv_city_name).text = weatherItem.name
//        findViewById<TextView>(R.id.rv_degrees).text = weatherItem.main.temp.toString()
//        findViewById<TextView>(R.id.rv_week_day).text = getWeekDay()
//        findViewById<TextView>(R.id.rv_weather_description).text =
//            weatherItem.weather.first().main
//        val imageView = findViewById<AppCompatImageView>(R.id.rv_weather_icon)
//        AppUtils.setWeatherIcon(context, imageView, weatherItem.weather.first().id)
//
//    }
//}
//
//private var onItemClickListener: ((CurrentWeatherModel) -> Unit)? = null
//
//fun setOnItemClickListener(listener: (CurrentWeatherModel) -> Unit) {
//    onItemClickListener = listener
//}
//
//@SuppressLint("SimpleDateFormat")
//fun getWeekDay(): String {
//    val sdf = SimpleDateFormat("EEEE")
//    val date = Date()
//    return (sdf.format(date))
//}
//
////
////
//
//private val differCallback = object : DiffUtil.ItemCallback<CurrentWeatherModel>() {
//    override fun areItemsTheSame(
//        oldItem: CurrentWeatherModel,
//        newItem: CurrentWeatherModel
//    ): Boolean {
//        return oldItem.coord == newItem.coord
//    }
//
//    override fun areContentsTheSame(
//        oldItem: CurrentWeatherModel,
//        newItem: CurrentWeatherModel
//    ): Boolean {
//        return oldItem == newItem
//    }
//}
//
//val differ = AsyncListDiffer(this, differCallback)
//
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
//    val binding =
//        RvWeatherCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    return WeatherListViewHolder(binding)
//}
//
//override fun getItemCount(): Int {
//    return differ.currentList.size
//}
//
//@SuppressLint("SetTextI18n")
//override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
//    val weatherItem = differ.currentList[position]
//    holder.bind(weatherItem)
//    holder.itemView.setOnClickListener {
//        onItemClickListener?.let { it(weatherItem) }
//    }
//
//}
//
//inner class WeatherListViewHolder(private val itemBinding: RvWeatherCellBinding) :
//    RecyclerView.ViewHolder(itemBinding.root) {
//    @SuppressLint("SetTextI18n")
//    fun bind(weatherItem: CurrentWeatherModel) {
//        itemBinding.apply {
//            rvCityName.text = weatherItem.name
//            rvDegrees.text = weatherItem.main.temp.toInt().toString() + "°C"
//            rvWeekDay.text = getWeekDay()
//            rvWeatherDescription.text = weatherItem.weather.first().main
//            rvFeelLike.text = weatherItem.main.feelLike.toInt().toString() + "°C"
//            rvHumidityValue.text = weatherItem.main.humidity.toInt().toString() + "%"
//            rvWindSpeed.text = weatherItem.wind.speed.toInt().toString() + " м/с"
//            AppUtils.setWeatherIcon(
//                itemView.context,
//                rvWeatherIcon,
//                weatherItem.weather.first().id
//            )
//
//        }
//    }
//}
//
//private var onItemClickListener: ((CurrentWeatherModel) -> Unit)? = null
//
//fun setOnItemClickListener(listener: (CurrentWeatherModel) -> Unit) {
//    onItemClickListener = listener
//}
//
//@SuppressLint("SimpleDateFormat")
//fun getWeekDay(): String {
//    val sdf = SimpleDateFormat("EEEE")
//    val date = Date()
//    return (sdf.format(date))
//}