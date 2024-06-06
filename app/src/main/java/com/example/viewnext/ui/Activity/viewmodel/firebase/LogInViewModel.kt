package com.example.viewnext.ui.Activity.viewmodel.firebase


import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LogInViewModel : ViewModel() {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    fun togglePasswordVisibility(currentTransformationMethod: Any): Any {
        return if (currentTransformationMethod == PasswordTransformationMethod.getInstance()) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }
}
