package com.example.viewnext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/**
import com.example.viewnext.ui.Activity.Firebase.LogIn
import com.example.viewnext.ui.Activity.viewmodel.firebase.LogInViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LogInTest {

    @Mock
    lateinit var viewModel: LogInViewModel

    @Mock
    lateinit var logInActivity: LogIn

    @Before
    fun setup() {
        logInActivity = Mockito.spy(LogIn())
    }

    @Test
    fun testSignInWithEmailAndPasswordSuccess() {
        val email = "test@example.com"
        val password = "password"

        Mockito.`when`(logInActivity.editTextUsuario.text.toString()).thenReturn(email)
        Mockito.`when`(logInActivity.editTextContraseña.text.toString()).thenReturn(password)
        Mockito.`when`(
            viewModel.signInWithEmailAndPassword(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any()
            )
        ).thenAnswer {
            val callback = it.arguments[2] as (Boolean) -> Unit
            callback.invoke(true) // Simulate successful sign-in
        }

        logInActivity.setup()

        Mockito.verify(logInActivity).navigateToPrincipalActivity()
    }

    @Test
    fun testSignInWithEmailAndPasswordFailure() {
        val email = "test@example.com"
        val password = "password"

        Mockito.`when`(logInActivity.editTextUsuario.text.toString()).thenReturn(email)
        Mockito.`when`(logInActivity.editTextContraseña.text.toString()).thenReturn(password)
        Mockito.`when`(
            viewModel.signInWithEmailAndPassword(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any()
            )
        ).thenAnswer {
            val callback = it.arguments[2] as (Boolean) -> Unit
            callback.invoke(false) // Simulate failed sign-in
        }

        logInActivity.setup()

        Mockito.verify(logInActivity).showAlert("Error", "Se ha producido un error al iniciar sesión")
    }
}

*/
