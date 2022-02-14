package com.example.realmdatabase

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class ContactLiveData : MutableLiveData<List<Contact>>() {

    override fun onActive() {
        super.onActive()
        Log.d("ContactLiveData", "ContactLiveData - onActive")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("ContactLiveData", "ContactLiveData - onInactive")
    }
}