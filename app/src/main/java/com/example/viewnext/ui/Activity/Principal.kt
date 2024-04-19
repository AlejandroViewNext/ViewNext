package com.example.viewnext.ui.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas
import com.example.viewnext.ui.Activity.Practicas.Practica2.SmartSolar
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class Principal  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        val practica1: LinearLayout = findViewById(R.id.practica1)
        val arrowButton1: ImageButton = findViewById(R.id.arrowButton)



        val btn_open_external_browser = findViewById<Button>(R.id.btn_open_external_browser)
        val btn_open_webview = findViewById<Button>(R.id.btn_open_webview)

        btn_open_external_browser.setOnClickListener {
            openExternalBrowser()
        }

        btn_open_webview.setOnClickListener {
            openWebview()
        }
        arrowButton1.setOnClickListener {

            val intent = Intent(this, ListaFacturas::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val arrowButton2: ImageButton = findViewById(R.id.arrowButton2)
        arrowButton2.setOnClickListener {

            val intent = Intent(this, SmartSolar::class.java)
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
    private fun openExternalBrowser() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iberdrola.es"))
        startActivity(intent)
    }

    private fun openWebview() {
        val webView = WebView(this)
        setContentView(webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.iberdrola.es")
    }
}