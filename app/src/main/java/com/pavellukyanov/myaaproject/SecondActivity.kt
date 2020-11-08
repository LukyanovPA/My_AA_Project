package com.pavellukyanov.myaaproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    val tvSecond: TextView = findViewById(R.id.tvSecond)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        initUI()
        goMyIntentService()
    }

    private fun goMyIntentService() {
        val intentMyIntentService = Intent(this, MyIntentService::class.java)
        startService(intentMyIntentService.putExtra("time", 3).putExtra("task", "Погладь кота"))
        startService(intentMyIntentService.putExtra("time", 1).putExtra("task", "Покорми кота"))
        startService(intentMyIntentService.putExtra("time", 4).putExtra("task", "Поиграй с котом"))
    }

//    private fun initUI() {
//        val tvSecond: TextView = findViewById(R.id.tvSecond)
//    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra(MyIntentService.EXTRA_KEY_OUT)
            tvSecond.setText(result)
        }
    }
}

