package com.temp.weathertestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.activity.viewModels
import com.temp.weathertestapp.R
import com.temp.weathertestapp.base.BaseActivity
import com.temp.weathertestapp.ui.fragment.CityWeatherListFragment
import com.temp.weathertestapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    val viewModel: MainWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(CityWeatherListFragment(), CityWeatherListFragment.TAG)
    }
}