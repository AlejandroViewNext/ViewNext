package com.example.viewnext
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
class Principal  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        val arrowButton1: ImageButton = findViewById(R.id.arrowButton)
        arrowButton1.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val arrowButton2: ImageButton = findViewById(R.id.arrowButton2)
        arrowButton2.setOnClickListener {

            val intent = Intent(this,SmartSolar::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


}