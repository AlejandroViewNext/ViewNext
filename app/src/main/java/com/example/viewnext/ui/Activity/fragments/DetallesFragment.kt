package com.example.viewnext.ui.Activity.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewnext.R
import com.google.gson.Gson
import java.io.InputStreamReader
import android.widget.TextView
import com.example.viewnext.data.retromock.DetallesData
import android.app.AlertDialog
import android.widget.Button
import android.widget.ImageView

class DetallesFragment : Fragment() {

    private lateinit var cauTextView: TextView
    private lateinit var estadoTextView: TextView
    private lateinit var tipoAutoconsumoTextView: TextView
    private lateinit var compensacionExcedentesTextView: TextView
    private lateinit var potenciaInstalacionTextView: TextView
    private lateinit var infoImageButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detalles_fragment, container, false)


        cauTextView = view.findViewById(R.id.cauTextView)
        estadoTextView = view.findViewById(R.id.estadoTextView)
        tipoAutoconsumoTextView = view.findViewById(R.id.tipoAutoconsumoTextView)
        compensacionExcedentesTextView = view.findViewById(R.id.compensacionExcedentesTextView)
        potenciaInstalacionTextView = view.findViewById(R.id.potenciaInstalacionTextView)
        infoImageButton = view.findViewById(R.id.custom_alert_dialog)


        infoImageButton.setOnClickListener {
            showCustomAlertDialog()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val inputStream = resources.openRawResource(R.raw.instalacion_data)
        val reader = InputStreamReader(inputStream)
        val instalacionData = Gson().fromJson(reader, DetallesData::class.java)

        cauTextView.text = instalacionData.cau
        estadoTextView.text = instalacionData.estadoSolicitud
        tipoAutoconsumoTextView.text = instalacionData.tipoAutoconsumo
        compensacionExcedentesTextView.text = instalacionData.compensacionExcedentes
        potenciaInstalacionTextView.text = instalacionData.potenciaInstalacion
    }

    private fun showCustomAlertDialog() {

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_dialog, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogDescription = dialogView.findViewById<TextView>(R.id.dialogDescription)
        val dialogButton = dialogView.findViewById<Button>(R.id.dialogButton)


        dialogTitle.text = "Estado solicitud alta de consumidor"
        dialogDescription.text = "El tiempo estimado de activación de tu autoconsumo es de 1 a 2 meses, este variará en función de tu comunidad autónoma y distribuidora"


        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)

        val dialog = builder.create()
        dialog.show()

        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}
