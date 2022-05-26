package com.temp.weathertestapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.FragmentMoreWeatherBinding
import com.temp.weathertestapp.ui.MainWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreWeatherFragment : BaseFragment() {

    private lateinit var binding: FragmentMoreWeatherBinding
    val viewModel: MainWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    companion object{
        val TAG: String = MoreWeatherFragment::class.java.simpleName
    }

}