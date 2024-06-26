package com.example.viewnext.data.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import java.text.SimpleDateFormat
import java.util.Locale

class FacturasAdapter(private val facturas: List<Facturas.Factura>, private val context: Context) :
    RecyclerView.Adapter<FacturasAdapter.FacturaViewHolder>() {

    private lateinit var clickListener: ListaFacturas_Activity

    fun setItemClickListener(clickListener: ListaFacturas_Activity) {
        this.clickListener = clickListener
    }

    class FacturaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
        val tvImporte: TextView = view.findViewById(R.id.tvImporte)
        val btn: ImageButton = view.findViewById(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_factura, parent, false)
        return FacturaViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val factura = facturas[position]

        val formattedDate = formatDate(factura.fecha)

        if (factura.descEstado == "Pendiente de pago") {
            holder.tvEstado.text = factura.descEstado
            holder.tvEstado.visibility = View.VISIBLE
        } else {
            holder.tvEstado.visibility = View.GONE
        }

        holder.tvFecha.text = formattedDate
        holder.tvImporte.text = "${factura.importeOrdenacion}€"

        holder.btn.setOnClickListener {
            clickListener.onItemFacturaClicked()
        }
    }

    override fun getItemCount(): Int {
        return facturas.size
    }

    private fun formatDate(dateStr: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateStr)
        return outputFormat.format(date)
    }
}
