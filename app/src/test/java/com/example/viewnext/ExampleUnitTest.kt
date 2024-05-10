package com.example.viewnext

/**
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