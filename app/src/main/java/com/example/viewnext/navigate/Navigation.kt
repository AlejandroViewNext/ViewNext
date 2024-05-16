package com.example.viewnext.navigate


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.example.viewnext.ui.Activity.Firebase.ForgotPassword
import com.example.viewnext.ui.Activity.Firebase.SignUp
import com.example.viewnext.ui.Activity.Practicas.Practica1.FiltroFacturaActivity
import com.example.viewnext.ui.Activity.Principal_Activity

class Navigation {
    fun navigateToPrincipalActivity(context: Context) {
        val intent = Intent(context, Principal_Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }
/*
    fun navigateToLogIn(context: Context) {
        val intent = Intent(context, LogIn::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }
*/
    fun navigateToSignUp(context: Context) {
        val intent = Intent(context, SignUp::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    fun navigateToForgotPassword(context: Context) {
        val intent = Intent(context, ForgotPassword::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    fun navigateToFiltro(context: Context) {
        val intent = Intent(context, FiltroFacturaActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }


    fun showAlert(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //guardar usuario en shared preferences
    fun saveUsernameToSharedPreferences(context: Context, username: String) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }
}