package org.example.examen.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import enunciadoexamen.composeapp.generated.resources.Res
import enunciadoexamen.composeapp.generated.resources.logoE
import org.example.examen.ui.theme.BlueDark
import org.example.examen.ui.theme.BlueMedium
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScreenBienvenida(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.logoE),
            contentDescription = "Logo Empleo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Bienvenido a la App de Empleo",
            style = MaterialTheme.typography.headlineMedium,
            color = BlueDark
        )
        Button(
            onClick = { navController.navigate(Pantallas.Inscripcion.name) },
            colors = ButtonDefaults.buttonColors(containerColor = BlueMedium)
        ) {
            Text("Comenzar Inscripción", color = Color.White)
        }
    }
}