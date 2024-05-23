package com.example.viewnext.ui.Activity.Practicas.Practica1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewnext.R
import com.example.viewnext.data.retrofit.FacturaApiService
import com.example.viewnext.data.retrofit.Facturas
import com.example.viewnext.data.retrofit.FacturasAdapter
import com.example.viewnext.data.room.AppDatabase
import com.example.viewnext.data.room.FacturaDao
import com.example.viewnext.data.room.FacturaEntity
import com.example.viewnext.navigate.Navigation
import com.google.android.material.appbar.MaterialToolbar
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaFacturas_Activity : AppCompatActivity() {

    private lateinit var facturasApiResponse: List<Facturas.Factura>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FacturasAdapter
    private lateinit var facturasDao: FacturaDao
    private lateinit var client: HttpClient
    private lateinit var retrofitService: FacturaApiService

    private var fechaDesde = "07/02/2000"
    private var fechaHasta = "07/02/2024"
    private var importeMinimo = 0
    private var importeMaximo = 300
    private var pagadas = false
    private var anuladas = false
    private var cuotaFija = false
    private var pendientesPago = false
    private var planPago = false

    private val retrofitUrl = "https://viewnextandroid2.wiremockapi.cloud/facturas"
    private val retromockUrl = "https://viewnextandroid2.wiremockapi.cloud/retromock_facturas"

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        val navigation = Navigation()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_facturas)
        recyclerView = findViewById(R.id.recyclerViewFacturas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setOnClickListener {
            navigation.navigateToPrincipalActivity(this@ListaFacturas_Activity)
        }

        val database = AppDatabase.getDatabase(applicationContext)
        facturasDao = database.facturaDao()

        val switchRetrofit: Switch = findViewById(R.id.switch_retrofit)
        setupKtorClient()
        setupRetrofit()

        switchRetrofit.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                loadFacturasWithKtor(retromockUrl)
            } else {
                loadFacturasWithKtor(retrofitUrl)
            }
        }

        val btnFiltro = findViewById<ImageButton>(R.id.btnFiltro)
        btnFiltro.setOnClickListener {
            navigation.navigateToFiltro(this)
        }

        val extras = intent.extras
        extras?.let {
            fechaDesde = it.getString("fechaDesde", "07/02/2000")
            fechaHasta = it.getString("fechaHasta", "07/02/2024")
            importeMinimo = it.getInt("importeMinimo", 0)
            importeMaximo = it.getInt("importeMaximo", Int.MAX_VALUE)
            pagadas = it.getBoolean("pagadas", false)
            anuladas = it.getBoolean("anuladas", false)
            cuotaFija = it.getBoolean("cuotaFija", false)
            pendientesPago = it.getBoolean("pendientesPago", false)
            planPago = it.getBoolean("planPago", false)
        }

        loadFacturasWithKtor(retrofitUrl) // Cargar Ktor inicialmente
    }

    private fun setupKtorClient() {
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    contentType = ContentType.Application.Json,
                    json = kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // Opcional: Ignorar claves desconocidas en el JSON
                    })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viewnextandroid2.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitService = retrofit.create(FacturaApiService::class.java)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadFacturasWithKtor(url: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response: String = withContext(Dispatchers.IO) {
                    client.get(url).bodyAsText()
                }
                var facturaApiResponse= Json.decodeFromString<Facturas.ApiResponse>(response)
                this@ListaFacturas_Activity.facturasApiResponse = facturaApiResponse.facturas
                applyFiltersAndLoadAdapter()
                Toast.makeText(
                    applicationContext,
                    "Vista con Ktor",
                    Toast.LENGTH_SHORT
                ).show()

                GlobalScope.launch {
                    facturasDao.deleteAllFacturas()
                    facturaApiResponse.facturas.forEach { factura ->
                        facturasDao.insertFactura(
                            FacturaEntity(
                                fecha = factura.fecha,
                                importeOrdenacion = factura.importeOrdenacion,
                                descEstado = factura.descEstado
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    "Error al cargar con Ktor",
                    Toast.LENGTH_LONG
                ).show()
                Log.e("oliwis",e.message.toString())
            }
        }
    }

    private fun applyFiltersAndLoadAdapter() {
        val facturasFiltradas = facturasApiResponse.filter { factura ->
            val fechaFactura = factura.fecha
            val importeFactura = factura.importeOrdenacion
            val estadoFactura = factura.descEstado

            val fechaDentroRango = fechaFactura in fechaDesde..fechaHasta
            val importeDentroRango = importeFactura >= importeMinimo && importeFactura <= importeMaximo
            val estadoCoincide = when {
                pagadas && estadoFactura.equals("Pagada", ignoreCase = true) -> true
                anuladas && estadoFactura.equals("Anulada", ignoreCase = true) -> true
                cuotaFija && estadoFactura.equals("Cuota Fija", ignoreCase = true) -> true
                pendientesPago && estadoFactura.equals("Pendiente de pago", ignoreCase = true) -> true
                planPago && estadoFactura.equals("Plan de Pago", ignoreCase = true) -> true
                else -> false
            }

            fechaDentroRango || importeDentroRango || estadoCoincide
        }

        adapter = FacturasAdapter(facturasFiltradas, this@ListaFacturas_Activity)
        adapter.setItemClickListener(this@ListaFacturas_Activity)
        recyclerView.adapter = adapter
    }

    fun onItemFacturaClicked() {
        showCustomAlertDialog()
    }

    private fun showCustomAlertDialog() {
        val builder = android.app.AlertDialog.Builder(this)

        builder.setTitle("Información")
        builder.setMessage("Esta funcionalidad no está disponible.")

        builder.setPositiveButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
