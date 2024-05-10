package com.example.viewnext.ui.Activity.Firebase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.viewnext.R

@Composable
fun ListaFacturasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Logo()
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            hint = "Correo Electrónico",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            hint = "Contraseña",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            RememberMeCheckbox()
            Spacer(modifier = Modifier.width(8.dp))
            ForgotPasswordText()
        }
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(onClick = {})
        Spacer(modifier = Modifier.height(16.dp))
        OrRegisterButton(onClick = {})
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo_iberdrola_cli),
        contentDescription = null,
        modifier = Modifier
            .width(204.dp)
            .height(70.dp)
    )
}

@Composable
fun InputField(hint: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(text = hint) },
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .shadow(4.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
fun PasswordField(hint: String, modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        placeholder = { Text(text = hint) },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .shadow(4.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
fun RememberMeCheckbox() {
    var checked by remember { mutableStateOf(false) }

    Checkbox(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun ForgotPasswordText() {
    Text(
        text = "He olvidado mis datos",
        color = Color.Blue
    )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .shadow(4.dp)
    ) {
        Text(text = "Entrar", style = MaterialTheme.typography.button)
    }
}

@Composable
fun OrRegisterButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
            .shadow(4.dp)
    ) {
        Text(text = "Registrarse", style = MaterialTheme.typography.button, color = Color.Green)
    }
}

@Preview
@Composable
fun PreviewListaFacturasScreen() {
    MaterialTheme {
        ListaFacturasScreen()
    }
}


