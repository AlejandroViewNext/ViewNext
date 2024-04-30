package com.example.viewnext.ui.Activity.viewmodel.firebase


import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordViewModel : ViewModel() {
    private val firebaseAuth: FirebaseAuth = Firebase.auth

    fun sendPasswordReset(email: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
    }
}
