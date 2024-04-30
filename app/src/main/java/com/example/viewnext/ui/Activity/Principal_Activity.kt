package com.example.viewnext.ui.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.viewmodel.PrincipalViewModel


class Principal_Activity : AppCompatActivity() {

    private lateinit var viewModel: PrincipalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        viewModel = ViewModelProvider(this).get(PrincipalViewModel::class.java)

        val practica1: LinearLayout = findViewById(R.id.practica1)
        val arrowButton1: ImageButton = findViewById(R.id.arrowButton)
        val btn_open_external_browser = findViewById<Button>(R.id.btn_open_external_browser)
        val btn_open_webview = findViewById<Button>(R.id.btn_open_webview)

        btn_open_external_browser.setOnClickListener {
            viewModel.openExternalBrowser(this)
        }

        btn_open_webview.setOnClickListener {
            viewModel.openWebview(this)
        }

        arrowButton1.setOnClickListener {
            viewModel.openListaFacturasActivity(this)
        }

        val arrowButton2: ImageButton = findViewById(R.id.arrowButton2)
        arrowButton2.setOnClickListener {
            viewModel.openSmartSolarActivity(this)
        }

        // Remote Config
        val showPractica = viewModel.checkPracticaVisibility(this)
        if (showPractica) {
            practica1.visibility = View.VISIBLE
        } else {
            practica1.visibility = View.INVISIBLE
        }
    }
}
