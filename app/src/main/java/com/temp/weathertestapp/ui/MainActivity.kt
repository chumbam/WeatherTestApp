package com.temp.weathertestapp.ui

import android.os.Bundle
import android.util.Log.e
import android.view.View
import androidx.activity.viewModels
import com.temp.weathertestapp.R
import com.temp.weathertestapp.base.BaseActivity
import com.temp.weathertestapp.databinding.ActivityMainBinding
import com.temp.weathertestapp.ui.fragment.CityWeatherListFragment
import com.temp.weathertestapp.ui.fragment.MoreWeatherFragment
import com.temp.weathertestapp.utils.FabButtonClick
import com.temp.weathertestapp.utils.Resource
import com.temp.weathertestapp.utils.WeatherAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainWeatherViewModel by viewModels()
    var mListener: FabButtonClick? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        viewModel.weatherAppState.observe(this) { screenState ->
            when (screenState) {
                is WeatherAppState.Loading -> {
                    showLoading(true)
                    e("MainActivity", "Loading")
                }
                is WeatherAppState.CurrentWeatherListScreenState -> {
                    showLoading(false)
                    openFragment(CityWeatherListFragment(), CityWeatherListFragment.TAG)
                    e("MainActivity", "CurrentWeatherScreen")
                }
                is WeatherAppState.MoreWeatherScreenState -> {
                    showLoading(false)
                    openFragmentWithArgs(
                        MoreWeatherFragment(),
                        MoreWeatherFragment.TAG,
                        screenState.data
                    )
                    e("MainActivity", "MoreWeatherScreen")
                }
                is WeatherAppState.Error -> {
                    showLoading(false)
                    e("MainActivityError", screenState.message)
                }
                else ->{}
            }
        }
        binding.floatingActionButton.setOnClickListener {
            mListener?.onFabClick()
        }
    }

    private fun showLoading(status: Boolean) {
        binding.progressBar.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    fun setListener(listener: FabButtonClick){
       this.mListener = listener
    }
}