package com.example.viewnext.ui.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Firebase.LogIn


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
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

        //remote config
val configSettings= remoteConfigSettings {
    minimumFetchIntervalInSeconds= 15
}
val firebaseConfig: FirebaseRemoteConfig= Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)
firebaseConfig.setDefaultsAsync(mapOf("show_menu" to false,"Show_Colors" to false))
    }
}
