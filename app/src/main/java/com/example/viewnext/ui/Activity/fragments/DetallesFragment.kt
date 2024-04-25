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

class DetallesFragment : Fragment() {

    private lateinit var cauTextView: TextView
    private lateinit var estadoTextView: TextView
    private lateinit var tipoAutoconsumoTextView: TextView
    private lateinit var compensacionExcedentesTextView: TextView
    private lateinit var potenciaInstalacionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.detalles_fragment, container, false)

        // Inicializar los componentes
        cauTextView = view.findViewById(R.id.cauTextView)
        estadoTextView = view.findViewById(R.id.estadoTextView)
        tipoAutoconsumoTextView = view.findViewById(R.id.tipoAutoconsumoTextView)
        compensacionExcedentesTextView = view.findViewById(R.id.compensacionExcedentesTextView)
        potenciaInstalacionTextView = view.findViewById(R.id.potenciaInstalacionTextView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lee el archivo JSON
        val inputStream = resources.openRawResource(R.raw.instalacion_data)
        val reader = InputStreamReader(inputStream)
        val instalacionData = Gson().fromJson(reader, DetallesData::class.java)

        // Asigna los valores a los TextViews
        cauTextView.text = instalacionData.cau
        estadoTextView.text = instalacionData.estadoSolicitud
        tipoAutoconsumoTextView.text = instalacionData.tipoAutoconsumo
        compensacionExcedentesTextView.text = instalacionData.compensacionExcedentes
        potenciaInstalacionTextView.text = instalacionData.potenciaInstalacion
    }
}
