package com.temp.weathertestapp.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.temp.weathertestapp.adapters.DetailWeatherListAdapter
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.FragmentMoreWeatherBinding
import com.temp.weathertestapp.models.CurrentWeatherModel
import com.temp.weathertestapp.ui.MainWeatherViewModel
import com.temp.weathertestapp.utils.AppUtils
import com.temp.weathertestapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MoreWeatherFragment : BaseFragment() {

    private lateinit var binding: FragmentMoreWeatherBinding
    private val viewModel: MainWeatherViewModel by activityViewModels()
    private var _detailWeatherAdapter: DetailWeatherListAdapter? = null
    private val detailWeatherAdapter
        get() = checkNotNull(_detailWeatherAdapter) {
            "Detail Weather Adapter isn't initialized"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val model = args?.getSerializable("args") as CurrentWeatherModel
        setWeather(model)
        setupRV()
    }

    override fun onStart() {
        super.onStart()
        viewModel.weeklyWeather.observe(this) {
            when (it) {
                is Resource.Success -> {
                    showLoading(false)
                    Log.e("MoreWeather", it.data.toString())
                    detailWeatherAdapter.differ.submitList(it.data?.daily)
                }
                is Resource.Error -> {
                    showLoading(false)

                    Log.e(
                        "MoreWeatherError2", it.message.toString()
                    )
                }
                is Resource.Loading -> {
                    showLoading(true)
                    Log.e("MoreWeather", "Loading")
                }
            }
        }
    }

    private fun setupRV() {
        _detailWeatherAdapter = DetailWeatherListAdapter()
        binding.fmwRecyclerview.apply {
            adapter = detailWeatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setWeather(model: CurrentWeatherModel) {
        binding.apply {
            fmwCity.text = model.name
            fmwDate.text = getDate()
            fmwTempCondition.text = model.main.temp.toInt().toString() + "°C"
            fmwCondition.text = model.weather.first().main
            AppUtils.setWeatherIcon(
                requireContext(),
                fmwWeatherResource,
                model.weather.first().id
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {
        val sdf = SimpleDateFormat("EEEE,MMMM,dd")
        val date = Date()
        return (sdf.format(date))
    }

    private fun showLoading(status: Boolean) {
        binding.fmwProgressBar.progressBar.visibility = if (status) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        val TAG: String = MoreWeatherFragment::class.java.simpleName
    }

}