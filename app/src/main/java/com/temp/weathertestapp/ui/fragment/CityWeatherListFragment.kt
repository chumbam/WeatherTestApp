package com.temp.weathertestapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.FragmentCityWeatherListBinding
import com.temp.weathertestapp.ui.MainWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherListFragment : BaseFragment() {

    private lateinit var binding: FragmentCityWeatherListBinding
    val viewModel: MainWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityWeatherListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    companion object{
        val TAG: String = CityWeatherListFragment::class.java.simpleName
    }


}