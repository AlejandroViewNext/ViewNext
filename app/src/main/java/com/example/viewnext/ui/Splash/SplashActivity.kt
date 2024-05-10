package com.example.viewnext.ui.Splash


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Principal_Activity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val username = getUsernameFromSharedPreferences()
            if (username != null) {
                // Si hay un nombre de usuario guardado, inicia sesión automáticamente
                val intent = Intent(this, Principal_Activity::class.java)
                startActivity(intent)
            } else {
                // Si no hay un nombre de usuario guardado, lleva al usuario a la pantalla de inicio de sesión
               // val intent = Intent(this, LogIn::class.java)
                startActivity(intent)
            }
            finish()
        }, SPLASH_TIME_OUT)

        //hacemos el remoteconfig al principio para k ya esté actualizado (15 seg entre momento de refrescarse)
val configSettings= remoteConfigSettings {
    minimumFetchIntervalInSeconds= 15
}
val firebaseConfig: FirebaseRemoteConfig= Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
firebaseConfig.setDefaultsAsync(mapOf("show_menu" to false,"Show_Colors" to false))
    }

    private fun getUsernameFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null)
    }
}
