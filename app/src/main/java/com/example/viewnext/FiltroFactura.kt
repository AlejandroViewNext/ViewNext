package com.example.viewnext


import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class FiltroFactura : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtro_factura)

        val seekBar = findViewById<SeekBar>(R.id.seekbar)

        // Establecer el valor mínimo y máximo
        seekBar.min = 1
        seekBar.max = 300

        // Escuchar cambios en la seekbar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Manejar el cambio de progreso aquí
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Método llamado cuando el usuario toca la seekbar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Método llamado cuando el usuario deja de tocar la seekbar
            }
        })
    }
}
