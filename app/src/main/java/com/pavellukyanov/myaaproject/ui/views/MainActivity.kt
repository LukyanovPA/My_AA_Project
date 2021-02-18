package com.pavellukyanov.myaaproject.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pavellukyanov.myaaproject.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("yyyy", Locale.getDefault().language)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragmetn, FragmentMoviesList.newInstance())
                .commit()
        }
    }
}