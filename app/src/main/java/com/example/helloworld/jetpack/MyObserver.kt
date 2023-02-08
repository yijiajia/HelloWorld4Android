package com.example.helloworld.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle : Lifecycle) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        val state = lifecycle.currentState
        Log.d("MyObserver","actionStart $state")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        val state = lifecycle.currentState
        Log.d("MyObserver","activity is stop $state")
    }

}