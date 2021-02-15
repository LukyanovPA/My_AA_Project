package com.pavellukyanov.myaaproject.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pavellukyanov.myaaproject.R
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.api.Router
import com.pavellukyanov.myaaproject.ui.base.MainActivityViewModelFactory
import com.pavellukyanov.myaaproject.ui.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        viewModel.loadConfig()
        viewModel.getConfig().observe(this, { config ->
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.flFragmetn, FragmentMoviesList.newInstance(config))
                    .commit()
            }
        })
    }

    private fun initViewModel() {
        val factory = MainActivityViewModelFactory(MoviesRemoteRepo(Router().movieApi))
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }
}