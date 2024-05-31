package com.example.camionapi.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MyAppConfig {
    private val _isConect = MutableLiveData<Boolean>(false)
    val isConect: LiveData<Boolean> get() = _isConect

    fun setConnectionStatus(status: Boolean) {
        _isConect.value = status
    }

    private val _token = MutableLiveData<String>("")
    val token: LiveData<String> get() = _token

    fun setToken(Token: String) {
        _token.value = Token
    }

    private val _isTokenValid = MutableLiveData<Boolean>(false)
    val isTokenValid: LiveData<Boolean> get() = _isTokenValid

    fun validToken(result: Boolean) {
        _isTokenValid.value = result
    }
}
