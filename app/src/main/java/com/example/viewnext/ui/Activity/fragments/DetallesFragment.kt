package com.example.viewnext.ui.Activity.fragments
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewnext.R
import com.example.viewnext.data.retromock.DetallesData
import com.example.viewnext.ui.Activity.viewmodel.fragments.DetallesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesFragment : Fragment() {

    private lateinit var detallesViewModel: DetallesViewModel
    private lateinit var cauTextView: TextView
    private lateinit var estadoTextView: TextView
    private lateinit var tipoAutoconsumoTextView: TextView
    private lateinit var compensacionExcedentesTextView: TextView
    private lateinit var potenciaInstalacionTextView: TextView
    private lateinit var customAlertDialogButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detalles_fragment, container, false)
        detallesViewModel = ViewModelProvider(this).get(DetallesViewModel::class.java)
        cauTextView = view.findViewById(R.id.cauTextView)
        estadoTextView = view.findViewById(R.id.estadoTextView)
        tipoAutoconsumoTextView = view.findViewById(R.id.tipoAutoconsumoTextView)
        compensacionExcedentesTextView = view.findViewById(R.id.compensacionExcedentesTextView)
        potenciaInstalacionTextView = view.findViewById(R.id.potenciaInstalacionTextView)
        customAlertDialogButton = view.findViewById(R.id.custom_alert_dialog)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detallesViewModel.loadData(requireContext())
        detallesViewModel.detallesData.observe(viewLifecycleOwner) { detallesData ->
            populateUI(detallesData)
        }

        customAlertDialogButton.setOnClickListener {
            showCustomAlertDialog()
        }
    }

    private fun populateUI(detallesData: DetallesData) {
        cauTextView.text = detallesData.cau
        estadoTextView.text = detallesData.estadoSolicitud
        tipoAutoconsumoTextView.text = detallesData.tipoAutoconsumo
        compensacionExcedentesTextView.text = detallesData.compensacionExcedentes
        potenciaInstalacionTextView.text = detallesData.potenciaInstalacion
    }

    private fun showCustomAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        val dialogTitleTextView = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogDescriptionTextView = dialogView.findViewById<TextView>(R.id.dialogDescription)
        val dialogButton = dialogView.findViewById<Button>(R.id.dialogButton)

        dialogTitleTextView.text = getString(R.string.estado_solicitud_autoconsumo)
        dialogDescriptionTextView.text = getString(R.string.detalles_PopPup)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}
