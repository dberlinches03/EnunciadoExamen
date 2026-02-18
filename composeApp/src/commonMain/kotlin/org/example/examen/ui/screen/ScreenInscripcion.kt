package org.example.examen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.examen.models.Persona

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenInscripcion(onGuardar: (Persona) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf(18) }
    var sexo by remember { mutableStateOf("Masculino") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var habilidades by remember { mutableStateOf("") }

    val opcionesSexo = listOf("Masculino", "Femenino", "Otro", "No desea compartirlo")
    val opcionesEdad = (18..67).toList()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = dni, onValueChange = { dni = it }, label = { Text("DNI") }, modifier = Modifier.fillMaxWidth())

        // Selector Edad
        var expEdad by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expEdad, onExpandedChange = { expEdad = !expEdad }) {
            OutlinedTextField(
                value = edad.toString(), onValueChange = {}, readOnly = true,
                label = { Text("Edad") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expEdad) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expEdad, onDismissRequest = { expEdad = false }) {
                opcionesEdad.forEach { e ->
                    DropdownMenuItem(text = { Text(e.toString()) }, onClick = { edad = e; expEdad = false })
                }
            }
        }

        // Selector Sexo
        var expSexo by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expSexo, onExpandedChange = { expSexo = !expSexo }) {
            OutlinedTextField(
                value = sexo, onValueChange = {}, readOnly = true,
                label = { Text("Sexo") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expSexo) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expSexo, onDismissRequest = { expSexo = false }) {
                opcionesSexo.forEach { s ->
                    DropdownMenuItem(text = { Text(s) }, onClick = { sexo = s; expSexo = false })
                }
            }
        }

        OutlinedTextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = habilidades, onValueChange = { habilidades = it }, label = { Text("Habilidades") }, modifier = Modifier.fillMaxWidth().height(100.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { /* Limpiar todos los campos */
                nombre = ""; dni = ""; direccion = ""; email = ""; telefono = ""; habilidades = ""
            }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                Text("Borrar")
            }
            Button(onClick = {
                onGuardar(Persona(nombre, dni, edad, sexo, direccion, email, telefono, habilidades))
                nombre = ""; dni = ""; direccion = ""; email = ""; telefono = ""; habilidades = ""
            }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF308885))) {
                Text("Guardar")
            }
        }
    }
}