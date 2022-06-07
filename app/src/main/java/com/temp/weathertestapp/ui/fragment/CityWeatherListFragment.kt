package com.temp.weathertestapp.ui.fragment


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.temp.weathertestapp.adapters.WeatherListAdapter
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.AddCityDialogViewBinding
import com.temp.weathertestapp.databinding.FragmentCityWeatherListBinding
import com.temp.weathertestapp.ui.MainActivity
import com.temp.weathertestapp.ui.MainWeatherViewModel
import com.temp.weathertestapp.utils.FabButtonClick
import com.temp.weathertestapp.utils.Resource
import com.temp.weathertestapp.utils.WeatherAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityWeatherListFragment : BaseFragment(), FabButtonClick {

    private lateinit var binding: FragmentCityWeatherListBinding
    private val viewModel: MainWeatherViewModel by activityViewModels()
    private var addCityDialog: Dialog? = null
    private lateinit var dialogBinding: AddCityDialogViewBinding
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
        setupDialog()
        setFabListener()
        observableViewModel()

        dialogBinding.apply {
            dialogOkBtn.setOnClickListener {
                val city = dialogEditText.text.toString()
                if (city.isNotEmpty()) {
                    viewModel.getCurrentWeather(city)
                    addCityDialog?.cancel()
                }
            }
        }

        weatherAdapter.setOnItemClickListener {
            Log.e(TAG, "ClickListener")
            viewModel.getWeeklyWeather(it)
            viewModel.obtainNavigationEvent(WeatherAppState.MoreWeatherScreenState(it))
        }
    }

    private fun observableViewModel() {
        viewModel.currWeather.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    showLoading(false)
                    Log.e(
                        "CityWeatherLIst", it.data.toString()
                    )
                    it.data?.let { it1 -> weatherAdapter.setData(it1) }
//                    it.data?.first()?.let { it1 -> viewModel.getWeeklyWeather(it1) }
                }
                is Resource.Error -> {
                    showLoading(false)

                    Log.e("CityWeatherLIstError", it.message.toString())
                }
                is Resource.Loading -> {
                    showLoading(true)
                    Log.e("CityWeatherLIst", "Loading")
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun setupDialog() {
        addCityDialog = context?.let { Dialog(it) }
        dialogBinding = AddCityDialogViewBinding.inflate(layoutInflater)
        addCityDialog?.setContentView(dialogBinding.root)
    }

    private fun setupRV() {
        _weatherAdapter = WeatherListAdapter()
        binding.fcwlRvCurrentWeather.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showLoading(status: Boolean) {
        binding.fcwlProgress.progressBar.visibility = if (status) View.VISIBLE else View.INVISIBLE
    }

    private fun setFabListener() {
        (activity as MainActivity).setListener(this)
    }

    companion object {
        val TAG: String = CityWeatherListFragment::class.java.simpleName
    }

    override fun onFabClick() {
        addCityDialog?.show()
    }

}