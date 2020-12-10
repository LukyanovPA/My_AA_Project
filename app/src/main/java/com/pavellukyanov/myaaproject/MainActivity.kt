package com.pavellukyanov.myaaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pavellukyanov.myaaproject.fragments.FragmentMoviesList

class MainActivity : AppCompatActivity()/*, MovieInstance*/ {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragmetn, FragmentMoviesList())
                .commit()
        }
    }
}