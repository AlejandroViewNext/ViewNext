package com.example.viewnext.ui.Activity.Firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Principal_Activity
import com.example.viewnext.ui.Activity.viewmodel.firebase.LogInViewModel


class LogIn: AppCompatActivity()  {
    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        val botonEntrar = findViewById<Button>(R.id.botonEntrar)
        val imagenLogo = findViewById<ImageView>(R.id.imageView)
        val botonRegistro = findViewById<Button>(R.id.botonRegistrar)

        imagenLogo.setOnClickListener {
            navigateToPrincipalActivity()
        }

        botonRegistro.setOnClickListener {
            navigateToSignUp()
        }

        val textViewOlvidadoDatos = findViewById<TextView>(R.id.textViewOlvidadoDatos)
        textViewOlvidadoDatos.setOnClickListener {
            navigateToForgotPassword()
        }

        val editTextContraseña = findViewById<EditText>(R.id.editTextContraseña)
        val botonMostrarContraseña = findViewById<ImageButton>(R.id.imageViewPassword)
        botonMostrarContraseña.setOnClickListener {
            val newTransformationMethod = viewModel.togglePasswordVisibility(editTextContraseña.transformationMethod)
            editTextContraseña.transformationMethod = newTransformationMethod as TransformationMethod?
        }

        setup()

        setupRemoteConfig()
    }

    private fun setup() {
        title = "Inicio"
        val botonEntrar = findViewById<Button>(R.id.botonEntrar)
        val editTextUsuario = findViewById<EditText>(R.id.editTextUsuario)
        val editTextContraseña = findViewById<EditText>(R.id.editTextContraseña)

        botonEntrar.setOnClickListener {
            val email = editTextUsuario.text.toString()
            val password = editTextContraseña.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signInWithEmailAndPassword(email, password) { isSuccess ->
                    if (isSuccess) {
                        navigateToPrincipalActivity()
                    } else {
                        showAlert("Error", "Se ha producido un error al iniciar sesión")
                    }
                }
            } else if (password.isEmpty()) {
                showAlert("Error", "Datos incorrectos")
            } else if (email.isEmpty()) {
                showAlert("Error", "Datos incorrectos")
            }
        }
    }

    private fun setupRemoteConfig() {
        val colorFake = ContextCompat.getColor(this, R.color.black)
        val colorFake2 = ContextCompat.getColor(this, R.color.white)
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val showColor : Boolean = Firebase.remoteConfig.getBoolean("Show_Colors")
                if (showColor){
                    val botonRegistro = findViewById<Button>(R.id.botonRegistrar)
                    val botonEntrar = findViewById<Button>(R.id.botonEntrar)
                    botonRegistro.setBackgroundColor(colorFake)
                    botonEntrar.setBackgroundColor(colorFake)
                    botonRegistro.setTextColor(colorFake2)
                    botonEntrar.setTextColor(colorFake2)
                    botonEntrar.setText("Ya tengo una cuenta")
                    botonRegistro.setText("Terminar Registro")
                }
            }
        }
    }

    private fun navigateToPrincipalActivity() {
        val intent = Intent(this, Principal_Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUp::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun navigateToForgotPassword() {
        val intent = Intent(this, ForgotPassword::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}