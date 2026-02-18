package org.example.examen

import ScreenInscripcion
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import org.jetbrains.compose.resources.painterResource
import enunciadoexamen.composeapp.generated.resources.Res
import enunciadoexamen.composeapp.generated.resources.logoE
import org.example.examen.models.Persona
import org.example.examen.ui.screen.ScreenSolicitudes

// Colores oficiales del enunciado
val BlueDeep = Color(0xFF1C2751)
val BlueDark = Color(0xFF344C92)
val GreenGray = Color(0xFF8A9586)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
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
                        modifier = Modifier.size(50.dp).padding(8.dp)
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val pantallas = listOf("Bienvenida", "Inscripcion", "Solicitudes", "Ayuda")
                pantallas.forEach { route ->
                    NavigationBarItem(
                        label = { Text(route) },
                        selected = currentRoute == route,
                        onClick = { navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }},
                        icon = { Icon(Icons.Default.List, contentDescription = null) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(navController, "Bienvenida", Modifier.padding(padding)) {
            composable("Bienvenida") { ScreenBienvenida() }
            composable("Inscripcion") {
                ScreenInscripcion(onGuardar = { listaPersonas.add(it) })
            }
            composable("Solicitudes") {
                ScreenSolicitudes(listaPersonas, onBorrar = { listaPersonas.remove(it) })
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
        Image(painter = painterResource(Res.drawable.logoE), contentDescription = null, modifier = Modifier.size(150.dp))
        Text("Bienvenido a Empleo", style = MaterialTheme.typography.headlineMedium)
    }
}