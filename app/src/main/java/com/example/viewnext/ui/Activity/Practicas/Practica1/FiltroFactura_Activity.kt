package com.example.viewnext.ui.Activity.Practicas.Practica1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.TextView
import com.example.viewnext.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import java.text.SimpleDateFormat
import java.util.*

class FiltroFactura_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtro_factura)

        val slider = findViewById<Slider>(R.id.slider)
        val rangeSelectedText = findViewById<TextView>(R.id.range_selected_text)
        val botonEsquina = findViewById<ImageButton>(R.id.boton_esquina)
        val editTextDesde = findViewById<MaterialButton>(R.id.editText_desde)
        val editTextHasta = findViewById<MaterialButton>(R.id.editText_hasta)
        val eliminarFiltros = findViewById<Button>(R.id.eliminar_filtros)
        val filtrar = findViewById<Button>(R.id.filtrar)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)

        botonEsquina.setOnClickListener {
            val intent = Intent(this, ListaFacturas_Activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        slider.addOnChangeListener { _, value, _ ->
            val selectedValueText = "1€  -   ${value.toInt()}€"
            rangeSelectedText.text = selectedValueText
        }

        editTextDesde.setOnClickListener {
            showDatePickerDialog(editTextDesde)
        }

        editTextHasta.setOnClickListener {
            showDatePickerDialog(editTextHasta)
        }

        eliminarFiltros.setOnClickListener {
            eliminarFiltros.setOnClickListener {

                checkbox1.isChecked = false
                checkbox2.isChecked = false
                checkbox3.isChecked = false
                checkbox4.isChecked = false
                checkbox5.isChecked = false


                editTextDesde.text = "día/mes/año"
                editTextHasta.text = "día/mes/año"
                slider.value = slider.valueFrom


                val defaultRangeText = " "
                rangeSelectedText.text = defaultRangeText
            }
        }

        filtrar.setOnClickListener {
            val fechaDesde = editTextDesde.text.toString()
            val fechaHasta = editTextHasta.text.toString()
            val importeMinimo = slider.valueFrom.toInt()
            val importeMaximo = slider.value.toInt()
            val pagadas = checkbox1.isChecked
            val anuladas = checkbox2.isChecked
            val cuotaFija = checkbox3.isChecked
            val pendientesPago = checkbox4.isChecked
            val planPago = checkbox5.isChecked

            val intent = Intent(this, ListaFacturas_Activity::class.java)
            intent.putExtra("fechaDesde", fechaDesde)
            intent.putExtra("fechaHasta", fechaHasta)
            intent.putExtra("importeMinimo", importeMinimo)
            intent.putExtra("importeMaximo", importeMaximo)
            intent.putExtra("pagadas", pagadas)
            intent.putExtra("anuladas", anuladas)
            intent.putExtra("cuotaFija", cuotaFija)
            intent.putExtra("pendientesPago", pendientesPago)
            intent.putExtra("planPago", planPago)
            startActivity(intent)

        }
    }

    private fun showDatePickerDialog(materialButton: MaterialButton) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                materialButton.text = formattedDate
            }, year, month, day)

        datePickerDialog.show()
    }

}
