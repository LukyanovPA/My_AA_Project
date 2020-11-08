package com.pavellukyanov.myaaproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

// статья подсказка http://developer.alexanderklimov.ru/android/activity.php
// статья подсказка http://developer.alexanderklimov.ru/android/theory/intentservice.php

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE = 1488
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startSecondActivity()
    }


    private fun startSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val tvMain: TextView = findViewById(R.id.tvMain)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val resultStr = data?.getStringExtra(SecondActivity.CODE)
                tvMain.setText(resultStr)
                Log.d("ReCode", "result code: $resultStr")
            } else {
                tvMain.setText("Нет данных")
            }
        }
    }

}