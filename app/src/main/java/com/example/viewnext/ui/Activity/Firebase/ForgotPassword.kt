package com.example.viewnext.ui.Activity.Firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewnext.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {
private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)
        firebaseAuth= Firebase.auth

        val botonLogIn = findViewById<Button>(R.id.botonLogIn)
        val correo = findViewById<EditText>(R.id.editTextCorreo)
        val enviarCorreo = findViewById<MaterialButton>(R.id.enviarCorreo)
        botonLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        enviarCorreo.setOnClickListener {

            sendPasswordReset(correo.text.toString())
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()

        }
    }
    
    private fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
             .addOnCompleteListener() { task ->
                 if(task.isSuccessful){
                     Toast.makeText(baseContext,"Correo enviado",Toast.LENGTH_SHORT).show()
                 }
                 else{
                     Toast.makeText(baseContext,"El correo no está registrado", Toast.LENGTH_SHORT).show()
                 }

        }
    }
}