package com.example.viewnext.ui.Activity.Practicas.Practica1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.viewmodel.practica1.FiltroFacturaViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import java.util.Calendar

class FiltroFacturaActivity : AppCompatActivity() {

    private lateinit var viewModel: FiltroFacturaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtro_factura)


        viewModel = ViewModelProvider(this)[FiltroFacturaViewModel::class.java]
        viewModel.setContext(this)
        val slider = findViewById<Slider>(R.id.slider)
        val rangeSelectedText = findViewById<TextView>(R.id.range_selected_text)
        val editTextDesde = findViewById<MaterialButton>(R.id.editText_desde)
        val editTextHasta = findViewById<MaterialButton>(R.id.editText_hasta)
        val eliminarFiltros = findViewById<Button>(R.id.eliminar_filtros)
        val filtrarButton = findViewById<Button>(R.id.filtrar)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)

        val botonEsquina = findViewById<ImageButton>(R.id.boton_esquina)


        botonEsquina.setOnClickListener {
            val intent = Intent(this, ListaFacturas_Activity::class.java)
            startActivity(intent)
        }
        slider.addOnChangeListener { _, value, _ ->
            val selectedValueText = "1€  -   ${value.toInt()}€"
            rangeSelectedText.text = selectedValueText
            viewModel.actualizarImporteMinimo(value.toInt())

        }

        editTextDesde.setOnClickListener {
            showDatePickerDialog(editTextDesde)
        }

        editTextHasta.setOnClickListener {
            showDatePickerDialog(editTextHasta)
        }

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
            viewModel.actualizarImporteMinimo(slider.valueFrom.toInt())
            viewModel.actualizarImporteMaximo(slider.value.toInt())
            viewModel.actualizarPagadas(false)
            viewModel.actualizarAnuladas(false)
            viewModel.actualizarCuotaFija(false)
            viewModel.actualizarPendientesPago(false)
            viewModel.actualizarPlanPago(false)
        }

        checkbox1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.actualizarPagadas(isChecked)
        }

        checkbox2.setOnCheckedChangeListener { _, isChecked ->
            viewModel.actualizarAnuladas(isChecked)
        }

        checkbox3.setOnCheckedChangeListener { _, isChecked ->
            viewModel.actualizarCuotaFija(isChecked)
        }

        checkbox4.setOnCheckedChangeListener { _, isChecked ->
            viewModel.actualizarPendientesPago(isChecked)
        }

        checkbox5.setOnCheckedChangeListener { _, isChecked ->
            viewModel.actualizarPlanPago(isChecked)
        }

        filtrarButton.setOnClickListener {
            viewModel.enviarDatos()
        }

        viewModel.actualizarImporteMinimo(slider.valueFrom.toInt())
        viewModel.actualizarImporteMaximo(slider.value.toInt())
    }


    private fun showDatePickerDialog(materialButton: MaterialButton) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val formattedDate = viewModel.obtenerFechaFormateada(selectedDate)
                materialButton.text = formattedDate
                if (materialButton.id == R.id.editText_desde) {
                    viewModel.actualizarFechaDesde(formattedDate)
                } else if (materialButton.id == R.id.editText_hasta) {
                    viewModel.actualizarFechaHasta(formattedDate)
                }
            }, year, month, day)
        datePickerDialog.show()
    }



}
