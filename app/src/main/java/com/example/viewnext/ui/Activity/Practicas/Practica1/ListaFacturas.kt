package com.example.viewnext.ui.Activity.Practicas.Practica1
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.infinum.retromock.Retromock
import com.example.viewnext.R
import com.example.viewnext.data.retrofit.Facturas
import com.example.viewnext.data.retrofit.FacturasAdapter
import com.example.viewnext.data.retromock.RetroMockFacturaApiService
import com.example.viewnext.data.room.AppDatabase
import com.example.viewnext.data.room.FacturaDao
import com.example.viewnext.data.room.FacturaEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaFacturas : AppCompatActivity() {

    private lateinit var facturasApiResponse: List<Facturas.Factura>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FacturasAdapter
    private lateinit var facturasDao: FacturaDao
    // private lateinit var service: FacturaApiService
   private lateinit var service: RetroMockFacturaApiService

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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }




        val retrofit = Retrofit.Builder()
            .baseUrl("https://viewnextandroid4.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // service = retrofit.create(FacturaApiService::class.java)


        val retromock: Retromock = Retromock.Builder()
            .retrofit(retrofit)
            //.defaultBodyFactory(ResourceBodyFactory())
            .build()

           service = retromock.create(RetroMockFacturaApiService::class.java)



        loadFacturas()
    }

    private fun loadFacturas() {

      service.getFacturas().enqueue(object : Callback<Facturas.ApiResponse> {
            override fun onResponse(call: Call<Facturas.ApiResponse>, response: Response<Facturas.ApiResponse>) {
                if (response.isSuccessful) {
                    facturasApiResponse = (response.body()?.facturas ?: emptyList()) as List<Facturas.Factura>
                    adapter = FacturasAdapter(facturasApiResponse, this@ListaFacturas)
                    recyclerView.adapter = adapter
                    Toast.makeText(applicationContext, "Todo correcto", Toast.LENGTH_SHORT).show()

                    // Insertar las facturas en la base de datos Room
                    GlobalScope.launch {
                        facturasDao.deleteAllFacturas()
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

            override fun onFailure(call: Call<Facturas.ApiResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al cargar los datos", Toast.LENGTH_LONG).show()
                Log.d("ERROR", t.toString())
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
}
