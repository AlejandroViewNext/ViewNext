package com.example.viewnext.ui.Activity.Firebase

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.navigate.Navigation
import com.example.viewnext.ui.Activity.viewmodel.firebase.SignUpViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUp : AppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        val botonRegistrar = findViewById<Button>(R.id.botonReg)
        val editTextUsuario = findViewById<EditText>(R.id.editTextUsuario)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContraseña)
        val botonLogIn = findViewById<Button>(R.id.botonLogIn)
        val botonMostrarContraseña = findViewById<ImageButton>(R.id.imageViewPassword)

        botonRegistrar.setOnClickListener {
            val email = editTextUsuario.text.toString()
            val password = editTextContrasena.text.toString()
            viewModel.registerUser(email, password)
        }

        botonLogIn.setOnClickListener {
            navigation.navigateToLogIn(this)
        }

        botonMostrarContraseña.setOnClickListener {
            val isPasswordVisible = editTextContrasena.transformationMethod == PasswordTransformationMethod.getInstance()
            editTextContrasena.transformationMethod = if (isPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
        }

        viewModel.registrationSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                navigation.navigateToLogIn(this)
            }
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                showAlert(errorMessage)
                viewModel.clearErrors()
            }
        })

        // Remote Config
        val colorFake = ContextCompat.getColor(this, R.color.black)
        val colorFake2 = ContextCompat.getColor(this, R.color.white)
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val showColor: Boolean = Firebase.remoteConfig.getBoolean("Show_Colors")
                if (showColor) {
                    botonLogIn.setBackgroundColor(colorFake)
                    botonRegistrar.setBackgroundColor(colorFake)
                    botonRegistrar.setTextColor(colorFake2)
                    botonLogIn.setTextColor(colorFake2)
                    botonLogIn.setText("Ya tengo una cuenta")
                    botonRegistrar.setText("Terminar Registro")
                }
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
