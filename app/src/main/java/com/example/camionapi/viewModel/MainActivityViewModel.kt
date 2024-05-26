package com.example.camionapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _isRegisterLinkClicked = MutableLiveData<Boolean>()
    val isRegisterLinkClicked: LiveData<Boolean> get() = _isRegisterLinkClicked

    fun onRegisterLinkClicked() {
        _isRegisterLinkClicked.value = true // Update the variable when clicked
    }
}
