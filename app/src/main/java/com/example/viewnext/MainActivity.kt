package com.example.viewnext

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import androidx.room.Room

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//Actualmente no estoy usando esto ya que me da fallos el import
import com.example.viewnext.FacturaApiService

class MainActivity : AppCompatActivity() {

    private lateinit var facturasApiResponse: List<Factura>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FacturasAdapter
    private lateinit var facturasDao: FacturaDao

//Arreglo temporal
    interface FacturaApiService {
        @GET("facturas")
        fun getFacturas(): Call<ApiResponse>
    }



    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerViewFacturas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = AppDatabase.getDatabase(applicationContext)
        facturasDao = database.facturaDao()

        val btnFiltro = findViewById<ImageButton>(R.id.btnFiltro)
        btnFiltro.setOnClickListener {
            val intent = Intent(this, FiltroFactura::class.java)
            startActivity(intent)
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("https://viewnextandroid.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        val service = retrofit.create(FacturaApiService::class.java)

        service.getFacturas().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    facturasApiResponse = response.body()?.facturas ?: emptyList()
                    adapter = FacturasAdapter(facturasApiResponse, this@MainActivity)
                    recyclerView.adapter = adapter
                    Toast.makeText(applicationContext, "Todo correcto", Toast.LENGTH_SHORT).show()

                    // Insertar las facturas en la base de datos Room
                    GlobalScope.launch {
                        facturasApiResponse.forEach { factura ->
                            facturasDao.insertFactura(
                                FacturaEntity(
                                    fecha = factura.fecha,
                                    importeOrdenacion = factura.importeOrdenacion,
                                    descEstado = factura.descEstado
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al cargar los datos", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun onItemFacturaClicked() {
        showCustomAlertDialog()
    }
    private fun showCustomAlertDialog() {
        val builder = android.app.AlertDialog.Builder(this)

        // Configurar título y mensaje
        builder.setTitle("Información")
        builder.setMessage("Esta funcionalidad no está disponible.")

        // Botón de cerrar
        builder.setPositiveButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }

        // Mostrar el AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
    data class Factura(
        val fecha: String,
        val importeOrdenacion: Double,
        val descEstado: String
    )


    data class ApiResponse(
        val numFacturas: Int,
        val facturas: List<Factura>
    )

    // Define el adaptador del RecyclerView
    class FacturasAdapter(private val facturas: List<Factura>, private val clickListener: MainActivity) : RecyclerView.Adapter<FacturasAdapter.FacturaViewHolder>() {

        class FacturaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val tvFecha: TextView = view.findViewById(R.id.tvFecha)
            val tvEstado: TextView = view.findViewById(R.id.tvEstado)
            val tvImporte: TextView = view.findViewById(R.id.tvImporte)
            val btn: Button = view.findViewById(R.id.btn) // Este es el botón definido en el layout item_factura
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_factura, parent, false)
            return FacturaViewHolder(view)
        }

        override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
            val factura = facturas[position]
            holder.tvFecha.text = factura.fecha
            holder.tvEstado.text = factura.descEstado
            holder.tvImporte.text = factura.importeOrdenacion.toString()

            holder.btn.setOnClickListener {
                clickListener.onItemFacturaClicked()
            }
        }

        override fun getItemCount() = facturas.size

    }
}