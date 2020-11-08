package com.pavellukyanov.myaaproject

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.util.concurrent.TimeUnit

class MyIntentService constructor(name: String = "myName") : IntentService(name) {
    val extraOut = "Кота накормили, погладили и поиграли с ним"

    companion object {
        const val TAG = "IntentServiceLog"
        const val EXTRA_KEY_OUT = "EXTRA_OUT"
        const val ACTION_MYINTENTSERVICE = "com.pavellukyanov.myaaproject.RESPONCE"
        const val ACTION_UPDATE = "com.pavellukyanov.myaaproject.UPDATE"
        const val EXTRA_KEY_UPDATE = "EXTRA_UPDATE"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onHandleIntent(intent: Intent?) {
        for (i in 0..100 step 10) {
            Thread.sleep(1000)

            val updateIntent = Intent()
            updateIntent.setAction(ACTION_UPDATE)
            updateIntent.addCategory(Intent.CATEGORY_DEFAULT)
            updateIntent.putExtra(EXTRA_KEY_UPDATE, i)
            sendBroadcast(updateIntent)
            Log.d("iI", "i = $i")
        }

//        val tm = intent?.getIntExtra("time", 0)
//        val label = intent?.getStringExtra("task")
//        Log.d(TAG, "onHandleIntent start: $label")
//
//        if (tm != null) {
//            TimeUnit.SECONDS.sleep(tm.toLong())
//        }
//        Log.d(TAG, "onHandleIntent end: $label")

        val responseIntent = Intent()
        responseIntent.setAction(ACTION_MYINTENTSERVICE)
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT)
        responseIntent.putExtra(EXTRA_KEY_OUT, extraOut)
        sendBroadcast(responseIntent)
    }
}