import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.examen.models.Persona

@Composable
fun ScreenInscripcion(onPersonaGuardada: (Persona) -> Unit) {
    // Estados para los campos
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var habilidades by remember { mutableStateOf("") }

    // Estados para desplegables
    var edad by remember { mutableStateOf(18) }
    var sexo by remember { mutableStateOf("Masculino") }

    val opcionesSexo = listOf("Masculino", "Femenino", "Otro", "No desea compartirlo")
    val opcionesEdad = (18..67).toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Por si el teclado tapa campos
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Formulario de Inscripción", style = MaterialTheme.typography.headlineSmall, color = Color(0xFF344C92))

        // Campos de Texto Simples
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre y Apellidos") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = dni, onValueChange = { dni = it }, label = { Text("DNI") }, modifier = Modifier.fillMaxWidth())

        // Selector de Edad (Lista desplegable 18-67)
        SelectorDesplegable(
            label = "Edad",
            opciones = opcionesEdad.map { it.toString() },
            seleccionado = edad.toString(),
            onSeleccion = { edad = it.toInt() }
        )

        // Selector de Sexo
        SelectorDesplegable(
            label = "Sexo",
            opciones = opcionesSexo,
            seleccionado = sexo,
            onSeleccion = { sexo = it }
        )

        OutlinedTextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth())

        // Campo Habilidades (Texto Grande)
        OutlinedTextField(
            value = habilidades,
            onValueChange = { habilidades = it },
            label = { Text("Habilidades") },
            modifier = Modifier.fillMaxWidth().height(120.dp),
            maxLines = 5
        )

        // Botones de Acción 
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    nombre = ""; dni = ""; direccion = ""; email = "";
                    telefono = ""; habilidades = ""; edad = 18; sexo = "Masculino"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A9586))
            ) {
                Text("Borrar Campos", color = Color.White)
            }

            Button(
                onClick = {
                    val nuevaPersona = Persona(nombre, dni, edad, sexo, direccion, email, telefono, habilidades)
                    onPersonaGuardada(nuevaPersona)
                    // Limpiar campos tras guardar 
                    nombre = ""; dni = ""; direccion = ""; email = "";
                    telefono = ""; habilidades = ""; edad = 18; sexo = "Masculino"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF308885))
            ) {
                    Text("Guardar", color = Color.White)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDesplegable(label: String, opciones: List<String>, seleccionado: String, onSeleccion: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().menuAnchor(),
            readOnly = true,
            value = seleccionado,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(text = opcion) },
                    onClick = {
                        onSeleccion(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}
