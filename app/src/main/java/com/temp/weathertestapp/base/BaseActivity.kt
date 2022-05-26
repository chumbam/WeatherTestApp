package com.temp.weathertestapp.base

import android.content.Intent
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.temp.weathertestapp.R

abstract class BaseActivity : AppCompatActivity() {

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    open fun openFragment(fragment: Fragment, Tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(Tag)
            .commit()
    }

    fun initToolbar(toolbar: androidx.appcompat.widget.Toolbar, title: String) {
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
        toolbar.navigationIcon?.isAutoMirrored = true
    }
}