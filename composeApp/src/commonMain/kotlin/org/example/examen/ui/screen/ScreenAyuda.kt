package org.example.examen.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp

@Composable
fun ScreenAyuda() {
    val uriHandler = LocalUriHandler.current
    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Ayuda", style = MaterialTheme.typography.headlineMedium)
        Text("\nEsta aplicación busca facilitarte la búsqueda de trabajo...")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { uriHandler.openUri("https://www.sepe.es/HomeSepe/es/") }) {
            Text("Ir al SEPE")
        }
    }
}