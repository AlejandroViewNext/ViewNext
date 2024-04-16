package com.example.viewnext

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class SingUp : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup)

        val botonRegistrar = findViewById<Button>(R.id.botonReg)
        val botonLogIn = findViewById<Button>(R.id.botonLogIn)


        botonLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        val editTextContraseña = findViewById<EditText>(R.id.editTextContraseña)
        val botonMostrarContraseña = findViewById<ImageButton>(R.id.imageViewPassword)
        botonMostrarContraseña.setOnClickListener {

            if (editTextContraseña.transformationMethod == PasswordTransformationMethod.getInstance()) {

                editTextContraseña.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {

                editTextContraseña.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        setup()

        val colorFake= ContextCompat.getColor(this,R.color.black)
        val colorFake2= ContextCompat.getColor(this,R.color.white)
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val showColor : Boolean = Firebase.remoteConfig.getBoolean("Show_Colors")
                if (showColor){
                    botonLogIn.setBackgroundColor(colorFake)
                    botonRegistrar.setBackgroundColor(colorFake)
                    botonRegistrar.setTextColor(colorFake2)
                    botonLogIn.setTextColor(colorFake2)
                    botonLogIn.setText("Ya tengo una cuenta")
                    botonRegistrar.setText("Terminar Registro")
                }
            }
        }
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

                            val intent = Intent(this, LogIn::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)

                    }else{
                            showAlert()
                    }
                }
            }else if( editTextContrasena.text.isEmpty()){
                AlertPassword()
            }
            else if(editTextUsuario.text.isEmpty()){
                AlertCorreo()
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha producido un error al registrarse")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }
    private fun AlertCorreo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Correo electronico no valido o vacio")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }
    private fun AlertPassword(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Contraseña vacia o no valida(debe tener al menos 6 caracteres, numeros y letras")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }
}