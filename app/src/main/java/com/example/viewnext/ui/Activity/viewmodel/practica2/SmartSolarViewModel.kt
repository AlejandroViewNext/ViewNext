package com.example.viewnext.ui.Activity.viewmodel.practica2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SmartSolarViewModel @Inject constructor(
) : ViewModel() {
    private val _navigateToMainActivity = MutableLiveData<Boolean>()
    val navigateToMainActivity: LiveData<Boolean>
        get() = _navigateToMainActivity
    fun onBackPressed() {
        _navigateToMainActivity.value = true
    }
}
