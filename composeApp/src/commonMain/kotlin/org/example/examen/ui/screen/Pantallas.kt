package org.example.examen.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

enum class Pantallas(val titulo: String, val icon: ImageVector) {
    Bienvenida("Inicio", Icons.Default.Home),
    Inscripcion("Inscripción", Icons.Default.Description),
    Solicitudes("Solicitudes", Icons.Default.List),
    Ayuda("Ayuda", Icons.Default.Help)
}