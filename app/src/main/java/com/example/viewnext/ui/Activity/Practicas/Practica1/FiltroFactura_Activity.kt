package com.example.viewnext.ui.Activity.Practicas.Practica1
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import java.util.Calendar

class FiltroFactura_Activity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                FiltroFacturaScreen()
            }
        }
    }
}
@Composable
fun FiltroFacturaScreen() {
    var desdeDate by remember { mutableStateOf(Calendar.getInstance()) }
    var hastaDate by remember { mutableStateOf(Calendar.getInstance()) }
    var importeRange by remember { mutableStateOf(1f) }
    var isChecked1 by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(false) }
    var isChecked3 by remember { mutableStateOf(false) }
    var isChecked4 by remember { mutableStateOf(false) }
    var isChecked5 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Filtrar Facturas",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Handle close icon click */ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        }
        Text(
            text = "Con Fecha de Emisión",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Desde",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
                TextButton(
                    onClick = { /* Show date picker for desdeDate */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${desdeDate.get(Calendar.DAY_OF_MONTH)}/${desdeDate.get(Calendar.MONTH)}/${desdeDate.get(Calendar.YEAR)}")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hasta",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
                TextButton(
                    onClick = { /* Show date picker for hastaDate */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${hastaDate.get(Calendar.DAY_OF_MONTH)}/${hastaDate.get(Calendar.MONTH)}/${hastaDate.get(Calendar.YEAR)}")
                }
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Por un Importe",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Slider(
            value = importeRange,
            onValueChange = { importeRange = it },
            valueRange = 1f..300f,
            steps = 299,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = "$1",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$300",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Por Estado",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Checkbox(
            checked = isChecked1,
            onCheckedChange = { isChecked1 = it },
            modifier = Modifier.padding(bottom = 8.dp)
        )
            Text("Pagadas")

        Checkbox(
            checked = isChecked2,
            onCheckedChange = { isChecked2 = it },
            modifier = Modifier.padding(bottom = 8.dp)
        )
            Text("Anuladas")

        Checkbox(
            checked = isChecked3,
            onCheckedChange = { isChecked3 = it },
            modifier = Modifier.padding(bottom = 8.dp)
        )
            Text("Cuota Fija")

        Checkbox(
            checked = isChecked4,
            onCheckedChange = { isChecked4 = it },
            modifier = Modifier.padding(bottom = 8.dp)
        )
            Text("Pendientes de Pago")

        Checkbox(
            checked = isChecked5,
            onCheckedChange = { isChecked5 = it },
            modifier = Modifier.padding(bottom = 8.dp)
        )
            Text("Plan de Pago")

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* eliminar_filtros*/ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Eliminar Filtros")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /*filtrar*/ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text("Filtrar")
            }
        }
    }
}
