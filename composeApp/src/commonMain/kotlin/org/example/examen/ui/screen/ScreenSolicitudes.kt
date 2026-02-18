package org.example.examen.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.examen.models.Persona
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

@Composable
fun ScreenSolicitudes(personas: List<Persona>, onBorrar: (Persona) -> Unit) {
    var textoFiltro by remember { mutableStateOf("") }
    var filtroAplicado by remember { mutableStateOf("") }
    var personaDetalle by remember { mutableStateOf<Persona?>(null) }

    val listaFiltrada = personas.filter { it.nombre.contains(filtroAplicado, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(value = textoFiltro, onValueChange = { textoFiltro = it }, label = { Text("Buscar nombre") }, modifier = Modifier.fillMaxWidth())

        Row(Modifier.padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { filtroAplicado = textoFiltro }, Modifier.weight(1f)) { Text("Filtrar") }
            Button(onClick = { textoFiltro = ""; filtroAplicado = "" }, Modifier.weight(1f)) { Text("Limpiar") }
        }

        LazyColumn {
            items(listaFiltrada) { p ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { personaDetalle = p }) {
                    Row(Modifier.padding(16.dp)) {
                        Text(p.nombre, Modifier.weight(1f))
                        IconButton(onClick = { onBorrar(p) }) { Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red) }
                    }
                }
            }
        }
    }

    if (personaDetalle != null) {
        AlertDialog(
            onDismissRequest = { personaDetalle = null },
            confirmButton = { TextButton(onClick = { personaDetalle = null }) { Text("Cerrar") } },
            title = { Text("Detalle de ${personaDetalle?.nombre}") },
            text = { Text("DNI: ${personaDetalle?.dni}\nEmail: ${personaDetalle?.email}\nHabilidades: ${personaDetalle?.habilidades}") }
        )
    }
}