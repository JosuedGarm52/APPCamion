package com.example.camionapi.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MyAppConfig {
    private val _isConect = MutableLiveData<Boolean>(false)
    val isConect: LiveData<Boolean> get() = _isConect

    fun setConnectionStatus(status: Boolean) {
        _isConect.value = status
    }
}
