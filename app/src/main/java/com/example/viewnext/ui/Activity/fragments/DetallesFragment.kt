package com.example.viewnext.ui.Activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.ui.Activity.viewmodel.fragments.DetallesViewModel

class DetallesFragment : Fragment() {
    private lateinit var detallesViewModel: DetallesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detallesViewModel = ViewModelProvider(this).get(DetallesViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {

                    DetallesContent()

            }
        }
    }

    @Composable
    fun DetallesContent() {
        val detallesData = detallesViewModel.detallesData.value
        Column {
            detallesData?.let { detalles ->
                Text(text = "CAU: ${detalles.cau}")
                Text(text = "Estado de Solicitud: ${detalles.estadoSolicitud}")
                Text(text = "Tipo de Autoconsumo: ${detalles.tipoAutoconsumo}")
                Text(text = "Compensación de Excedentes: ${detalles.compensacionExcedentes}")
                Text(text = "Potencia de Instalación: ${detalles.potenciaInstalacion}")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detallesViewModel.loadData(requireContext())
        detallesViewModel.detallesData.observe(viewLifecycleOwner) { detallesData ->
            // No es necesario hacer nada aquí, ya que la interfaz de usuario Compose se actualizará automáticamente.
        }
    }
}
