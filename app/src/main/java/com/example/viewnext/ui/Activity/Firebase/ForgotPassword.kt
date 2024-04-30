package com.example.viewnext.ui.Activity.Firebase


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.viewmodel.firebase.ForgotPasswordViewModel


class ForgotPassword : AppCompatActivity() {
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        val botonLogIn = findViewById<Button>(R.id.botonLogIn)
        val correo = findViewById<EditText>(R.id.editTextCorreo)
        val enviarCorreo = findViewById<Button>(R.id.enviarCorreo)

        botonLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        enviarCorreo.setOnClickListener {
            val email = correo.text.toString()
            viewModel.sendPasswordReset(email) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(baseContext, "Correo enviado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "El correo no está registrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
