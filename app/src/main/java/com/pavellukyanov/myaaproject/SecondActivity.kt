package com.pavellukyanov.myaaproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private val myBroadcastReceiver = MyBroadcastReceiver()
    var tvSecond: TextView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initUI()
        goMyIntentService()
    }

    private fun initUI() {
        tvSecond = findViewById(R.id.tvSecond)
    }

    private fun goMyIntentService() {
        val intentMyIntentService = Intent(this, MyIntentService::class.java)
        startService(intentMyIntentService.putExtra("time", 3).putExtra("task", "Погладь кота"))
        startService(intentMyIntentService.putExtra("time", 1).putExtra("task", "Покорми кота"))
        startService(intentMyIntentService.putExtra("time", 4).putExtra("task", "Поиграть с котом"))
        intentMyIntentService.putExtra("time", 4)
        intentMyIntentService.putExtra("task", "Поиграть с котом")
        startService(intentMyIntentService)

        val intentFilter = IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(myBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra(MyIntentService.EXTRA_KEY_OUT)
            tvSecond?.setText(result)
        }
    }
}

