package com.example.viewnext
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
class Principal  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        val arrowButton: ImageButton = findViewById(R.id.arrowButton)
        arrowButton.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}