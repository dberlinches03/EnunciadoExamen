package org.example.examen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import enunciadoexamen.composeapp.generated.resources.Res
import enunciadoexamen.composeapp.generated.resources.logoE
import org.jetbrains.compose.resources.painterResource
import org.example.examen.models.Persona
import org.example.examen.ui.screen.ScreenAyuda
import org.example.examen.ui.screen.ScreenInscripcion
import org.example.examen.ui.screen.ScreenSolicitudes

// Colores según enunciado
val BlueDeep = Color(0xFF1C2751)
val BlueDark = Color(0xFF344C92)
val GreenGray = Color(0xFF8A9586)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    // Vector de almacenamiento (Punto 2)
    val listaPersonas = remember { mutableStateListOf<Persona>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Empleo", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BlueDeep),
                navigationIcon = {
                    Image(
                        painter = painterResource(Res.drawable.logoE),
                        contentDescription = "Logo",
                        modifier = Modifier.size(45.dp).padding(8.dp)
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Punto 1: Menú de navegación
                val items = listOf(
                    Triple("Bienvenida", "Empleo", Icons.Default.Home),
                    Triple("Inscripcion", "Inscripción", Icons.Default.Person),
                    Triple("Solicitudes", "Solicitudes", Icons.AutoMirrored.Filled.List),
                    Triple("Ayuda", "Ayuda", Icons.Default.Info)
                )

                items.forEach { (route, label, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        selected = currentRoute == route,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo("Bienvenida") {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Bienvenida",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Bienvenida") { ScreenBienvenida() }
            composable("Inscripcion") {
                ScreenInscripcion(onGuardar = { nueva -> listaPersonas.add(nueva) })
            }
            composable("Solicitudes") {
                ScreenSolicitudes(
                    personas = listaPersonas,
                    onBorrar = { persona -> listaPersonas.remove(persona) }
                )
            }
            composable("Ayuda") { ScreenAyuda() }
        }
    }
}

@Composable
fun ScreenBienvenida() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.logoE),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Bienvenido a la Gestión de Empleo", style = MaterialTheme.typography.headlineMedium)
    }
}