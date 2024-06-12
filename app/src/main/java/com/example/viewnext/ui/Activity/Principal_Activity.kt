package com.example.viewnext.ui.Activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.viewmodel.PrincipalViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView


class Principal_Activity : AppCompatActivity() {

    private lateinit var viewModel: PrincipalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        viewModel = ViewModelProvider(this).get(PrincipalViewModel::class.java)
        val cerrarSesion = findViewById<MaterialButton>(R.id.CerrarSesion)
        val practica1 = findViewById<MaterialCardView>(R.id.practica1)
        val arrowButton1 = findViewById<View>(R.id.arrowButton)
        val practica2 = findViewById<MaterialCardView>(R.id.practica2)
        val btn_open_external_browser = findViewById<MaterialButton>(R.id.btn_open_external_browser)
        val btn_open_webview = findViewById<MaterialButton>(R.id.btn_open_webview)

        cerrarSesion.setOnClickListener {
            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            finish()
        }

        btn_open_external_browser.setOnClickListener {
            viewModel.openExternalBrowser(this)
        }

        btn_open_webview.setOnClickListener {
            openWebview(this)
        }

        practica1.setOnClickListener {
            viewModel.openListaFacturasActivity(this)
        }

        practica2.setOnClickListener {
            viewModel.openSmartSolarActivity(this)
        }

        // Remote Config
        val showPractica = viewModel.checkPracticaVisibility(this)
        if (showPractica) {
            practica1.visibility = View.VISIBLE
            practica2.visibility = View.VISIBLE
        } else {

            practica1.visibility = View.INVISIBLE
            practica2.visibility = View.INVISIBLE
        }
    }

    // esto hay que quitarlo
    fun openWebview(context: Context) {
        val webView = WebView(context)
        webView.webViewClient = WebViewClient()
        setContentView(webView)
        webView.loadUrl("https://www.iberdrola.com")

    }
}
