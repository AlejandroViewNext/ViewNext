package com.example.viewnext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LogIn: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)



        val botonRegistro = findViewById<Button>(R.id.botonRegistrar)
        botonRegistro.setOnClickListener {

            val intent = Intent(this, SingUp::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val textViewOlvidadoDatos = findViewById<TextView>(R.id.textViewOlvidadoDatos)
        textViewOlvidadoDatos.setOnClickListener {
            // Define la navegación hacia la actividad donde el usuario recupera sus datos
            val intent = Intent(this, ForgotPassword::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        setup()

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