package com.example.viewnext.ui.Activity.Firebase

import android.os.Bundle
import android.text.method.TransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.navigate.Navigation
import com.example.viewnext.ui.Activity.viewmodel.firebase.LogInViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class LogIn: AppCompatActivity()  {
    private lateinit var viewModel: LogInViewModel
    lateinit var editTextUsuario: EditText
    lateinit var editTextContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val navigation = Navigation()
        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)

        val imagenLogo = findViewById<ImageView>(R.id.imageView)
        val botonRegistro = findViewById<Button>(R.id.botonRegistrar)
        val textViewOlvidadoDatos = findViewById<TextView>(R.id.textViewOlvidadoDatos)
        val botonEntrar = findViewById<Button>(R.id.botonEntrar)

        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextContraseña = findViewById(R.id.editTextContraseña)

        imagenLogo.setOnClickListener {
            navigation.navigateToPrincipalActivity(this)
        }

        botonRegistro.setOnClickListener {
            navigation.navigateToSignUp(this)
        }

        textViewOlvidadoDatos.setOnClickListener {
            navigation.navigateToForgotPassword(this)
        }

        val botonMostrarContraseña = findViewById<ImageButton>(R.id.imageViewPassword)
        botonMostrarContraseña.setOnClickListener {
            val newTransformationMethod = viewModel.togglePasswordVisibility(editTextContraseña.transformationMethod)
            editTextContraseña.transformationMethod = newTransformationMethod as TransformationMethod?
        }

        setup()
        setupRemoteConfig()
    }

     fun setup() {
        title = "Inicio"
        val checkBoxRecordar = findViewById<CheckBox>(R.id.checkBoxRecordar)
         val navigation = Navigation()
        val botonEntrar = findViewById<Button>(R.id.botonEntrar)
        botonEntrar.setOnClickListener {
            val email = editTextUsuario.text.toString()
            val password = editTextContraseña.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signInWithEmailAndPassword(email, password) { isSuccess ->
                    if (isSuccess) {
                        if (checkBoxRecordar.isChecked) {
                            navigation.saveUsernameToSharedPreferences(this, email)
                        }
                        navigation.navigateToPrincipalActivity(this)
                    } else {
                        navigation.showAlert(this, "Error", "Se ha producido un error al iniciar sesión")
                    }
                }
            } else {
                navigation.showAlert(this, "Error", "Datos incorrectos")
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
}
