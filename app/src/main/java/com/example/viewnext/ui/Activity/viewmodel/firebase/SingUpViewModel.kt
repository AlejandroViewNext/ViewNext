package com.example.viewnext.ui.Activity.viewmodel.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean>
        get() = _registrationSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun registerUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _registrationSuccess.value = true
                } else {
                    _error.value = task.exception?.message ?: "Error"
                }
            }
    }

    fun clearErrors() {
        _error.value = null
    }
}
