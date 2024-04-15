package com.example.viewnext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class SingUp : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup)




        val botonLogIn = findViewById<Button>(R.id.botonLogIn)
        botonLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        setup()

    }
    private fun setup(){
        title= "Registro"
        val botonRegistrar = findViewById<Button>(R.id.botonReg)
        val editTextUsuario = findViewById<EditText>(R.id.editTextUsuario)
        val editTextContrasena = findViewById<EditText>(R.id.editTextContraseña)


        botonRegistrar.setOnClickListener {
            if(editTextUsuario.text.isNotEmpty()&& editTextContrasena.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextUsuario.text.toString(),
                    editTextContrasena.text.toString()).addOnCompleteListener {

                    if (it.isSuccessful){
                        botonRegistrar.setOnClickListener {
                            val intent = Intent(this, LogIn::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
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
        builder.setMessage("se ha producido un error al registrarse")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }

}