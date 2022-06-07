package com.temp.weathertestapp.ui.fragment


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.temp.weathertestapp.adapters.WeatherListAdapter
import com.temp.weathertestapp.base.BaseFragment
import com.temp.weathertestapp.databinding.AddCityDialogViewBinding
import com.temp.weathertestapp.databinding.FragmentCityWeatherListBinding
import com.temp.weathertestapp.models.CurrentWeatherModel
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
        setListeners()


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

    private fun setListeners() {

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

        val itemClickHelperCCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.bindingAdapterPosition
                val city = weatherAdapter.oldWeatherList[pos]
                viewModel.deleteCityFromDb(city)
                Snackbar.make(view!!, "Город успешно удалена", Snackbar.LENGTH_LONG).apply {
                    setAction("Отменить") {
                        viewModel.saveCityInDb(city)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemClickHelperCCallback).apply {
            attachToRecyclerView(binding.fcwlRvCurrentWeather)
        }
    }

    private fun setFabListener() {
        (activity as MainActivity).setListener(this)
    }

    override fun onFabClick() {
        addCityDialog?.show()
    }

    private fun showLoading(status: Boolean) {
        binding.fcwlProgress.progressBar.visibility = if (status) View.VISIBLE else View.INVISIBLE
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
//                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }
        }

        viewModel.getAllCityFromDb().observe(viewLifecycleOwner) {
            Log.e(TAG, "getAllCity")
            when (it) {
                null, emptyList<CurrentWeatherModel>() -> {
                    viewModel.getCurrentWeather("Moscow")
                    Log.e(TAG, "getAllCity = null")

                }
                else -> {
                    weatherAdapter.setData(it)
                    Log.e(TAG, "getAllCity" + it.toString())

                    Log.e(TAG, "getAllCity = notNull")

                }
            }
        }
    }

    companion object {
        val TAG: String = CityWeatherListFragment::class.java.simpleName
    }
}