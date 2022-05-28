package com.temp.weathertestapp.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.temp.weathertestapp.adapters.WeatherListAdapter
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.FragmentCityWeatherListBinding
import com.temp.weathertestapp.ui.MainWeatherViewModel
import com.temp.weathertestapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherListFragment : BaseFragment() {

    private lateinit var binding: FragmentCityWeatherListBinding
    private val viewModel: MainWeatherViewModel by activityViewModels()
    private var _weatherAdapter: WeatherListAdapter? = null
    private val weatherAdapter
        get() = checkNotNull(_weatherAdapter) {
            "Weather Adapter isn't initialized"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityWeatherListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentWeather()

        viewModel.currWeather.observe(this) {
            when (it) {
                is Resource.Success -> {
                    showLoading(false)
                    Log.e(
                        "MainActivity", it.data.toString())
                    weatherAdapter.differ.submitList(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)

                    Log.e(
                        "MainActivityError" +
                                "", it.message.toString()
                    )
                }
                is Resource.Loading -> {
                    showLoading(true)
                    Log.e(
                        "MainActivity","Loading")
                }

            }
        }

    }

    private fun setupRV(){
        _weatherAdapter = WeatherListAdapter()
        binding.fcwlRvCurrentWeather.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showLoading(status: Boolean){
      binding.fcwlProgress.progressBar.visibility = if(status) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        val TAG: String = CityWeatherListFragment::class.java.simpleName
    }

}