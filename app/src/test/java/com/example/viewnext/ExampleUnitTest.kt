package com.example.viewnext


import com.example.viewnext.ui.Activity.viewmodel.firebase.LogInViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class LogInTest {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var viewModel: LogInViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LogInViewModel(firebaseAuth)
    }

    // Prueba para el inicio de sesión con éxito
    @Test
    fun `test signInWithEmailAndPassword success`() {
        val email = "test@example.com"
        val password = "password"

        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(mockTask(true))

        viewModel.signInWithEmailAndPassword(email, password) { success ->
            assertTrue(success)
        }
    }

    // Método de utilidad para simular un Task de Firebase con un resultado específico
    private fun mockTask(success: Boolean): Task<AuthResult> {
        val mockTask = mock(Task::class.java) as Task<AuthResult>
        val mockResult = mock(AuthResult::class.java)

        `when`(mockTask.isSuccessful).thenReturn(success)
        `when`(mockTask.result).thenReturn(mockResult)

        return mockTask
    }
}

/*
import com.example.viewnext.data.retrofit.Facturas
import com.example.viewnext.data.retromock.RetroMockFacturaApiService
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class ListaFacturasActivityTest {
    @Mock
    private lateinit var mockApiService: RetroMockFacturaApiService
    private lateinit var activity: ListaFacturas_Activity

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
       // mockApiService = mock(RetroMockFacturaApiService::class.java)
        activity = ListaFacturas_Activity()
    }

    @Test
    fun `test cargar detalles de factura con RetroMock`() {
        runBlocking {
            {
                val mockFacturaResponse = Facturas.Factura(
                    fecha = "2024-06-05",
                    importeOrdenacion = 100.0,
                    descEstado = "Pagada"
                )
                val mockApiResponse = Facturas.ApiResponse(
                    numFacturas = listOf(mockFacturaResponse),
                    facturas = listOf(mockFacturaResponse)
                )

                val mockCall: Call<Facturas.ApiResponse> =
                    mock(Call::class.java) as Call<Facturas.ApiResponse>
                val mockResponse: Response<Facturas.ApiResponse> = Response.success(mockApiResponse)

                runBlocking {
                    `when`(mockCall.execute()).thenReturn(mockResponse)
                    `when`(mockApiService.getFacturas()).thenReturn(mockCall)
                }

                // Ejecutar el método para cargar detalles de factura utilizando RetroMock
                GlobalScope.launch(Dispatchers.Main) {
                    activity.loadFacturasWithRetroMock()
                }

                // Esperar hasta que la operación termine
                Thread.sleep(1000) // Simular espera suficiente para que la operación termine

                // Verificar que se ha obtenido una lista de facturas
                assertNotNull(activity.facturasApiResponse)
            }
        }
    }
}

 */

/*
import com.example.viewnext.data.retrofit.Facturas
import com.example.viewnext.data.room.FacturaDao
import com.example.viewnext.data.room.FacturaEntity
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListaFacturasActivityTest {

    private lateinit var listaFacturasActivity: ListaFacturas_Activity

    @Mock
    private lateinit var mockClient: HttpClient

    @Mock
    private lateinit var mockFacturasDao: FacturaDao

    @Mock
    private lateinit var mockApiResponse: Facturas.ApiResponse

    @Mock
    private lateinit var mockFactura: Facturas.Factura

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        listaFacturasActivity = ListaFacturas_Activity()
    }

    @Test
    fun testLoadFacturasWithKtor() {
        // Simulación de datos
        val url = "https://example.com/facturas"
        val facturasList = listOf(mockFactura)

        runBlocking {
            // Configuración de comportamiento del mockClient
            `when`(mockClient.get(url).bodyAsText()).thenReturn(mockApiResponse.toString())

            // Configuración de comportamiento del mockApiResponse
            `when`(mockApiResponse.facturas).thenReturn(facturasList)

            // Llamada al método a testear
            listaFacturasActivity.loadFacturasWithKtor(mockClient, url)

            // Verificación de resultados
            assertEquals(facturasList, listaFacturasActivity.facturasApiResponse)

            // Verificación de llamadas al DAO
            facturasList.forEach { factura ->
                verify(mockFacturasDao).insertFactura(
                    FacturaEntity(
                        fecha = factura.fecha,
                        importeOrdenacion = factura.importeOrdenacion,
                        descEstado = factura.descEstado
                    )
                )
            }

            // Verificación de llamada a deleteAllFacturas
            verify(mockFacturasDao).deleteAllFacturas()
        }
    }
}


 */



/*
import android.content.Context
import android.content.Intent
import com.example.viewnext.navigate.Navigation
import com.example.viewnext.ui.Activity.Principal_Activity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class NavigationTest {

    @Test
    fun testNavigateToPrincipalActivity() {
        // Crear un mock del contexto
        val mockContext = Mockito.mock(Context::class.java)
        // Instancia de la clase Navigation
        val navigation = Navigation()
        // Captor de la intención
        val intentCaptor = ArgumentCaptor.forClass(Intent::class.java)

        // Llamamos a la función que queremos testear
        navigation.navigateToPrincipalActivity(mockContext)

        // Verificamos que se llamó al método startActivity con cualquier Intent
        verify(mockContext).startActivity(intentCaptor.capture())

        // Obtenemos la intención capturada
        val capturedIntent = intentCaptor.value

        // Verificamos que la actividad destino es la correcta
        assertEquals(Principal_Activity::class.java.name, capturedIntent.component?.className)

        // Verificamos que se agregó el flag FLAG_ACTIVITY_CLEAR_TOP
        assertTrue(capturedIntent.flags and Intent.FLAG_ACTIVITY_CLEAR_TOP != 0)
    }
}


 */
/*
import com.example.viewnext.ui.Activity.viewmodel.firebase.LogInViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class LogInTest {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var viewModel: LogInViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LogInViewModel(firebaseAuth)
    }

    // Prueba para el inicio de sesión con éxito
    @Test
    fun `test signInWithEmailAndPassword success`() {
        val email = "test@example.com"
        val password = "password"

        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(mockTask(true))

        viewModel.signInWithEmailAndPassword(email, password) { success ->
            assertTrue(success)
        }
    }

    // Método de utilidad para simular un Task de Firebase con un resultado específico
    private fun mockTask(success: Boolean): Task<AuthResult> {
        val mockTask = mock(Task::class.java) as Task<AuthResult>
        val mockResult = mock(AuthResult::class.java)

        `when`(mockTask.isSuccessful).thenReturn(success)
        `when`(mockTask.result).thenReturn(mockResult)

        return mockTask
    }
}
 */
/**
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.viewnext.data.retrofit.Facturas
import com.example.viewnext.data.retrofit.FacturasAdapter
import com.example.viewnext.ui.Activity.Practicas.Practica1.ListaFacturas_Activity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock.*
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    private val context: Context = mock()

    @Before
    fun setup() {

    }

    @Test
    fun onBindViewHolder_correctDataDisplayed() {
        // Arrange
        val facturas = listOf(
            Facturas.Factura("01/01/2024", "Pendiente de pago", "100.0"),
            Facturas.Factura("02/02/2024", "Pagada", "150.0")
        )

        val adapter = FacturasAdapter(facturas)
        val view = LayoutInflater.from(context).inflate(R.layout.item_factura, null)
        val viewHolder = FacturasAdapter.FacturaViewHolder(view)

        // Act
        adapter.onBindViewHolder(viewHolder, 0)

        // Assert
        assertEquals("1 Jan 2024", viewHolder.tvFecha.text)
        assertEquals("Pendiente de pago", viewHolder.tvEstado.text)
        assertEquals("100.0€", viewHolder.tvImporte.text)
        assertTrue(viewHolder.tvEstado.visibility == View.VISIBLE)
    }

}



/**

import com.example.viewnext.data.retrofit.FacturasAdapter

import org.junit.Before


@RunWith(MockitoJUnitRunner::class)
class FacturasAdapterTest {

    // Mock de las facturas para usar en las pruebas
     val mockFacturas = listOf(
        Facturas.Factura("Pendiente de pago", 100.0, "01/01/2024"),
        Facturas.Factura("Pagada", 200.0, "02/02/2024")
    )

    // Mock del adaptador que será probado
    @Mock
    private lateinit var mockAdapter: FacturasAdapter

    @Before
    fun setup() {

        // Inicialización del adaptador con las facturas simuladas
        mockAdapter = FacturasAdapter(mockFacturas)
    }

    @Test
    fun testFacturasCount() {
        // Verificar si el número de facturas en el adaptador es correcto
        assertEquals(mockFacturas.size, mockAdapter.itemCount)
    }

    @Test
    fun testFacturaEstadoVisibility() {
        // Verificar si el estado de la factura es visible para las facturas pendientes de pago
        val facturaViewHolder = mockAdapter.onCreateViewHolder(null, 0)
        mockAdapter.onBindViewHolder(facturaViewHolder, 0)
        assertEquals(true, facturaViewHolder.tvEstado.visibility == View.VISIBLE)
    }

}

*/