package com.temp.weathertestapp.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.temp.weathertestapp.R

abstract class BaseFragment: Fragment() {

    lateinit var baseActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity
    }


    fun initToolBar(toolbar: Toolbar, title: String) {
        toolbar.title = title
        toolbar.setTitleTextColor(ContextCompat.getColor(baseActivity, R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        baseActivity.setSupportActionBar(toolbar)
        toolbar.navigationIcon?.isAutoMirrored = true
    }
}