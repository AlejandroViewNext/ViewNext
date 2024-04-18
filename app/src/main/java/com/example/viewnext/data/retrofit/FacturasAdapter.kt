package com.example.viewnext.data.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewnext.R
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas
import java.text.SimpleDateFormat
import java.util.Locale

class FacturasAdapter(private val facturas: List<Facturas.Factura>, private val clickListener: ListaFacturas) : RecyclerView.Adapter<FacturasAdapter.FacturaViewHolder>() {

    class FacturaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
        val tvImporte: TextView = view.findViewById(R.id.tvImporte)
        val btn: ImageButton = view.findViewById(R.id.btn) // Este es el botón definido en el layout item_factura
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_factura, parent, false)
        return FacturaViewHolder(view)
    }

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
        holder.tvEstado.text = factura.descEstado
        holder.tvImporte.text = "${factura.importeOrdenacion}€"

        holder.btn.setOnClickListener {
            clickListener.onItemFacturaClicked()
        }
    }

    override fun getItemCount() = facturas.size

    private fun formatDate(dateStr: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateStr)
        return outputFormat.format(date)
    }
}
