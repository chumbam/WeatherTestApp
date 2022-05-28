package com.temp.weathertestapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.repository.MainWeatherRepository
import com.temp.weathertestapp.utils.Constants
import com.temp.weathertestapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers


import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(private val repository: MainWeatherRepository) :
    ViewModel() {

    private val _currWeather: MutableLiveData<Resource<List<CurrentWeatherModel>>> = MutableLiveData()
    val currWeather: LiveData<Resource<List<CurrentWeatherModel>>> = _currWeather

    private val cityList: MutableList<CurrentWeatherModel> = mutableListOf()

    fun getCurrentWeather() = viewModelScope.launch {
        _currWeather.postValue(Resource.Loading())
        val response = repository.getCurrentWeather(
            "Moscow",
            Constants.UNITS,
            Constants.LANG,
            Constants.API_KEY
        )
        currentWeatherResponseHandle(response)
    }

    @SuppressLint("CheckResult")
    fun currentWeatherResponseHandle(resp: Single<CurrentWeatherModel>) {
        resp.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cityList.add(it)
                _currWeather.postValue(Resource.Success(cityList))
            }, {
                _currWeather.postValue(it.message?.let { it1 -> Resource.Error(it1) })
            }
            )
    }
}