package com.example.viewnext.ui.Activity.Practicas.Practica1
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.viewnext.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import java.text.SimpleDateFormat
import java.util.*

class FiltroFactura : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtro_factura)

        val slider = findViewById<Slider>(R.id.slider)
        val rangeSelectedText = findViewById<TextView>(R.id.range_selected_text)

        val botonEsquina = findViewById<ImageButton>(R.id.boton_esquina)

        val desde = findViewById<TextView>(R.id.desde)
        val editTextDesde = findViewById<MaterialButton>(R.id.editText_desde)
        val hasta = findViewById<TextView>(R.id.hasta)
        val editTextHasta = findViewById<MaterialButton>(R.id.editText_hasta)
        val importe = findViewById<TextView>(R.id.importe)
        val eliminarFiltros = findViewById<Button>(R.id.eliminar_filtros)
        val filtrar = findViewById<Button>(R.id.filtrar)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)

        botonEsquina.setOnClickListener {
            // Aquí puedes realizar alguna acción al hacer clic en el botón de la esquina
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
            val fechaDesde = editTextDesde.text.toString()
            val fechaHasta = editTextHasta.text.toString()
            val importeMinimo = slider.valueFrom.toInt()
            val importeMaximo = slider.value.toInt()
            val pagadas = checkbox1.isChecked
            val anuladas = checkbox2.isChecked
            val cuotaFija = checkbox3.isChecked
            val pendientesPago = checkbox4.isChecked
            val planPago = checkbox5.isChecked

            // Construir el mensaje para el Toast con todas las opciones elegidas
            val mensaje = "Filtros aplicados:\n" +
                    "Fecha desde: $fechaDesde\n" +
                    "Fecha hasta: $fechaHasta\n" +
                    "Importe mínimo: $importeMinimo\n" +
                    "Importe máximo: $importeMaximo\n" +
                    "Estado de las facturas:\n" +
                    "  - Pagadas: $pagadas\n" +
                    "  - Anuladas: $anuladas\n" +
                    "  - Cuota fija: $cuotaFija\n" +
                    "  - Pendientes de pago: $pendientesPago\n" +
                    "  - Plan de pago: $planPago"

            showFilterAlertDialog(mensaje)
        }

        filtrar.setOnClickListener {
            // Aquí puedes implementar la lógica para aplicar los filtros
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
    private fun showFilterAlertDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Filtros Aplicados")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}