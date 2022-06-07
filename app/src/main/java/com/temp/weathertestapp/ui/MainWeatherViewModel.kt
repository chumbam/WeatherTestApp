package com.temp.weathertestapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.models.WeeklyWeatherModel
import com.temp.weathertestapp.repository.MainWeatherRepository
import com.temp.weathertestapp.utils.Constants
import com.temp.weathertestapp.utils.Resource
import com.temp.weathertestapp.utils.WeatherAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers


import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(private val repository: MainWeatherRepository) :
    ViewModel() {

    private val _weatherAppState: MutableLiveData<WeatherAppState> =
        MutableLiveData(WeatherAppState.CurrentWeatherListScreenState)
    val weatherAppState: LiveData<WeatherAppState> = _weatherAppState

    private val _currWeather: MutableLiveData<Resource<List<CurrentWeatherModel>>> =
        MutableLiveData()
    val currWeather: LiveData<Resource<List<CurrentWeatherModel>>> = _currWeather

    private val _weeklyWeather: MutableLiveData<Resource<WeeklyWeatherModel>> =
        MutableLiveData()
    val weeklyWeather: LiveData<Resource<WeeklyWeatherModel>> = _weeklyWeather

    private val cityList: LinkedList<CurrentWeatherModel> = LinkedList()
    private val weeklyWeatherList: MutableList<WeeklyWeatherModel> = LinkedList()

    fun obtainNavigationEvent(event: WeatherAppState) {
        Log.e("ViewModel", event.toString())
        when (event) {
            is WeatherAppState.MoreWeatherScreenState -> {
                Log.e("ViewModel", "event")
                _weatherAppState.postValue(WeatherAppState.MoreWeatherScreenState(event.data))
            }
        }
    }

    fun getCurrentWeather(city: String) = viewModelScope.launch {
        _currWeather.postValue(Resource.Loading())
        val response = repository.getCurrentWeather(
            city,
            Constants.UNITS,
            Constants.LANG,
            Constants.API_KEY
        )
        currentWeatherResponseHandle(response)
    }

    //    fun getFiveDaysHourlyWeather(model: CurrentWeatherModel) = viewModelScope.launch {
//        _weeklyWeather.postValue(Resource.Loading())
//        val response = repository.getFiveDayWeather(
//            model.name,
//            Constants.UNITS, Constants.LANG, Constants.API_KEY
//        )
//        fiveDaysHourlyWeatherResponseHandle(response)
//    }
//
//    @SuppressLint("CheckResult")
//    private fun fiveDaysHourlyWeatherResponseHandle(resp: Single<FiveDaysWeatherModel>) {
//        resp.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                weeklyWeatherList.add(it)
//                _weeklyWeather.postValue(Resource.Success(weeklyWeatherList))
//            }, {
//                _weeklyWeather.postValue(it.message?.let { error -> Resource.Error(error) })
//            })
//    }
    fun getWeeklyWeather(model: CurrentWeatherModel) = viewModelScope.launch {
        _weeklyWeather.postValue(Resource.Loading())
        val response = repository.getWeeklyWeather(
            model.coord.lat,
            model.coord.lon,
            Constants.EXCLUDE_PARAM,
            Constants.UNITS,
            Constants.LANG,
            Constants.API_KEY
        )
        weeklyWeatherResponseHandle(response)
    }

    @SuppressLint("CheckResult")
    private fun weeklyWeatherResponseHandle(resp: Single<WeeklyWeatherModel>) {
        resp.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _weeklyWeather.postValue(Resource.Success(it))
            }, {
                _weeklyWeather.postValue(it.message?.let { error -> Resource.Error(error) })
            })
    }


    @SuppressLint("CheckResult")
    fun currentWeatherResponseHandle(resp: Single<CurrentWeatherModel>) {
        resp.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!cityList.contains(it)) {
                    cityList.addFirst(it)
                    _currWeather.postValue(Resource.Success(cityList))
                } else {
                    _currWeather.postValue(Resource.Error("Данный город уже присутсвует в списке"))
                }
            }, {
                _currWeather.postValue(it.message?.let { error -> Resource.Error(error) })
            }
            )
    }
}