package com.example.viewnext
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class Principal  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        val practica1: LinearLayout = findViewById(R.id.practica1)
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

    //remote config
        practica1.visibility= View.INVISIBLE
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{task ->
            if(task.isSuccessful){
                val showPractica : Boolean = Firebase.remoteConfig.getBoolean("show_menu")
                if (showPractica){
                    practica1.visibility= View.VISIBLE
                }
            }
        }
    }


}