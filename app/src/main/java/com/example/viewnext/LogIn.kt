package com.example.viewnext

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig


class LogIn: AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val botonEntrar = findViewById<Button>(R.id.botonEntrar)

        val botonRegistro = findViewById<Button>(R.id.botonRegistrar)
        botonRegistro.setOnClickListener {

            val intent = Intent(this, SingUp::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val textViewOlvidadoDatos = findViewById<TextView>(R.id.textViewOlvidadoDatos)
        textViewOlvidadoDatos.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        setup()

        val colorFake= ContextCompat.getColor(this,R.color.black)
        val colorFake2= ContextCompat.getColor(this,R.color.white)
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val showColor : Boolean = Firebase.remoteConfig.getBoolean("Show_Colors")
                if (showColor){
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
    private fun setup(){
        title= "Registro"
        val botonEntrar = findViewById<Button>(R.id.botonEntrar)
        val editTextUsuario = findViewById<EditText>(R.id.editTextUsuario)
        val editTextContraseña = findViewById<EditText>(R.id.editTextContraseña)


        botonEntrar.setOnClickListener {
            if(editTextUsuario.text.isNotEmpty()&& editTextContraseña.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextUsuario.text.toString(),
                    editTextContraseña.text.toString()).addOnCompleteListener {

                    if (it.isSuccessful){

                            val intent = Intent(this, Principal::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                    }else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha producido un error al Iniciar sesion")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}