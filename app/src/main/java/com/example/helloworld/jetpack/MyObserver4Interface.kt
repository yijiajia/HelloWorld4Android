package com.example.helloworld.jetpack

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver4Interface : DefaultLifecycleObserver {



    override fun onStart(owner: LifecycleOwner) {
       Log.d("MyObserver4Interface","MyObserver4Interface state is  onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("MyObserver4Interface","MyObserver4Interface state is  onStop")
    }
}