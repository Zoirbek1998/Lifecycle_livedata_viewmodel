package com.example.lifecyclelivedataviewmodel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver : DefaultLifecycleObserver {

    private val TAG = "MyObserver"

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "onResume: ")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "onPause: ")
    }

}