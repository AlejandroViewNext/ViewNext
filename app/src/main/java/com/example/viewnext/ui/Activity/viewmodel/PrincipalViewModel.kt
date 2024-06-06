package com.example.viewnext.ui.Activity.viewmodel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import com.example.viewnext.ui.Activity.Practicas.Practica2.SmartSolar_Activity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

class PrincipalViewModel : ViewModel() {

    fun checkPracticaVisibility(context: Context): Boolean {
        val showPractica: Boolean = Firebase.remoteConfig.getBoolean("show_menu")
        return showPractica
    }

    fun openListaFacturasActivity(context: Context) {
        val intent = Intent(context, ListaFacturas_Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    fun openSmartSolarActivity(context: Context) {
        val intent = Intent(context, SmartSolar_Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    fun openExternalBrowser(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iberdrola.es"))
        context.startActivity(intent)
    }


//No funciona, por ahora está en el activity
    fun openWebview(context: Context) {
        val webView = WebView(context)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.iberdrola.es")

    }
}
