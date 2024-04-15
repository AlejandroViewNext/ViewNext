package com.example.viewnext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LogIn: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val botonEntrar = findViewById<Button>(R.id.botonEntrar)
        botonEntrar.setOnClickListener {
            // Define la navegación hacia la actividad Principal
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }

        val botonRegistro = findViewById<Button>(R.id.botonRegistrar)
        botonRegistro.setOnClickListener {
            // Define la navegación hacia la actividad Principal
            val intent = Intent(this, SingUp::class.java)
            startActivity(intent)
        }

        val textViewOlvidadoDatos = findViewById<TextView>(R.id.textViewOlvidadoDatos)
        textViewOlvidadoDatos.setOnClickListener {
            // Define la navegación hacia la actividad donde el usuario recupera sus datos
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
    }
}