package com.example.viewnext
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        // Escuchar cambios en el Slider
        slider.addOnChangeListener { _, value, _ ->
            // Actualizar el texto para mostrar el valor seleccionado
            val selectedValueText = "1€  -   ${value.toInt()}€"
            rangeSelectedText.text = selectedValueText
        }

        // Obtener referencias a los TextInputEditText
        val editTextDesde = findViewById<MaterialButton>(R.id.editText_desde)
        val editTextHasta = findViewById<MaterialButton>(R.id.editText_hasta)

        // Asociar el método showDatePickerDialog al evento onClick de los TextInputEditText
        editTextDesde.setOnClickListener {
            showDatePickerDialog(editTextDesde)
        }

        editTextHasta.setOnClickListener {
            showDatePickerDialog(editTextHasta)
        }
    }

    // Método para mostrar el DatePickerDialog
    private fun showDatePickerDialog(materialButton: MaterialButton) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Setear la fecha seleccionada en el TextInputEditText
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                materialButton.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()
    }
}
