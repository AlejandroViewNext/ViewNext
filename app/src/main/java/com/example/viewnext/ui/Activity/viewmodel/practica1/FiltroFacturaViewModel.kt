package com.example.viewnext.ui.Activity.viewmodel.practica1

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import java.text.SimpleDateFormat
import java.util.*

class FiltroFacturaViewModel : ViewModel() {

    private val _context: MutableLiveData<Context> = MutableLiveData()
    val context: LiveData<Context> = _context

    private val _fechaDesde = MutableLiveData<String>()
    val fechaDesde: LiveData<String> = _fechaDesde

    private val _fechaHasta = MutableLiveData<String>()
    val fechaHasta: LiveData<String> = _fechaHasta

    private val _importeMinimo = MutableLiveData<Int>()
    val importeMinimo: LiveData<Int> = _importeMinimo

    private val _importeMaximo = MutableLiveData<Int>()
    val importeMaximo: LiveData<Int> = _importeMaximo

    private val _pagadas = MutableLiveData<Boolean>()
    val pagadas: LiveData<Boolean> = _pagadas

    private val _anuladas = MutableLiveData<Boolean>()
    val anuladas: LiveData<Boolean> = _anuladas

    private val _cuotaFija = MutableLiveData<Boolean>()
    val cuotaFija: LiveData<Boolean> = _cuotaFija

    private val _pendientesPago = MutableLiveData<Boolean>()
    val pendientesPago: LiveData<Boolean> = _pendientesPago

    private val _planPago = MutableLiveData<Boolean>()
    val planPago: LiveData<Boolean> = _planPago

    fun setContext(context: Context) {
        _context.value = context
    }

    init {
        // Inicializar los valores predeterminados
        _fechaDesde.value = "día/mes/año"
        _fechaHasta.value = "día/mes/año"
        _importeMinimo.value = 1
        _importeMaximo.value = 300
        _pagadas.value = false
        _anuladas.value = false
        _cuotaFija.value = false
        _pendientesPago.value = false
        _planPago.value = false
    }

    fun actualizarFechaDesde(fecha: String) {
        _fechaDesde.value = fecha
    }

    fun actualizarFechaHasta(fecha: String) {
        _fechaHasta.value = fecha
    }

    fun actualizarImporteMinimo(importe: Int) {
        _importeMinimo.value = importe
    }

    fun actualizarImporteMaximo(importe: Int) {
        _importeMaximo.value = importe
    }

    fun actualizarPagadas(valor: Boolean) {
        _pagadas.value = valor
    }

    fun actualizarAnuladas(valor: Boolean) {
        _anuladas.value = valor
    }

    fun actualizarCuotaFija(valor: Boolean) {
        _cuotaFija.value = valor
    }

    fun actualizarPendientesPago(valor: Boolean) {
        _pendientesPago.value = valor
    }

    fun actualizarPlanPago(valor: Boolean) {
        _planPago.value = valor
    }

    fun obtenerFechaFormateada(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun enviarDatos() {
        val contextValue = _context.value
        if (contextValue != null) {
            val intent = Intent(contextValue, ListaFacturas_Activity::class.java).apply {
                putExtra("fechaDesde", fechaDesde.value)
                putExtra("fechaHasta", fechaHasta.value)
                putExtra("importeMinimo", importeMaximo.value)
                putExtra("importeMaximo", importeMinimo.value)
                putExtra("pagadas", pagadas.value)
                putExtra("anuladas", anuladas.value)
                putExtra("cuotaFija", cuotaFija.value)
                putExtra("pendientesPago", pendientesPago.value)
                putExtra("planPago", planPago.value)
            }
            contextValue.startActivity(intent)
        }
    }




}
