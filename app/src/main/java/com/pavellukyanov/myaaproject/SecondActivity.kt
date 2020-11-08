package com.pavellukyanov.myaaproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    companion object {
        const val CODE = "com.pavellukyanov.myaaproject.MainActivity.REQUEST_CODE"
    }
    private val myBroadcastReceiver = MyBroadcastReceiver()
    private val myUpdateBroadcastReciver = MyUpdateBroadcastReciver()
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        progressBar = findViewById(R.id.progressBar)
        goMyIntentService()
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

        val updateIntentFilter = IntentFilter(MyIntentService.ACTION_UPDATE)
        updateIntentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(myUpdateBroadcastReciver, updateIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
        unregisterReceiver(myUpdateBroadcastReciver)
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra(MyIntentService.EXTRA_KEY_OUT)
            val intentMain = Intent(context, MainActivity::class.java)
            intentMain.putExtra(CODE, result)
            setResult(RESULT_OK, intentMain)
            Log.d("SecCode", "$result")
            finish()
        }
    }

    inner class MyUpdateBroadcastReciver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val update = intent?.getIntExtra(MyIntentService.EXTRA_KEY_UPDATE, 0)
            if (update != null) {
                progressBar?.setProgress(update)
                Log.d("ActI", "i = $update")
            }
        }

    }
}

