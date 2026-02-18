package org.example.examen

import ScreenInscripcion
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource

import enunciadoexamen.composeapp.generated.resources.Res
import enunciadoexamen.composeapp.generated.resources.logoE
import org.example.examen.models.Persona
import org.example.examen.ui.screen.Pantallas
import org.example.examen.ui.screen.ScreenBienvenida
import org.example.examen.ui.theme.BlueDeep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppEmpleo() {
    val navController = rememberNavController()

    val listaPersonas = remember { mutableStateListOf<Persona>() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text ("App Empleo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlueDeep,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    Image(
                        painter = painterResource(Res.drawable.logoE),
                        contentDescription = "Logo Empleo",
                        modifier = Modifier.size(40.dp).padding(8.dp)
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar (containerColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Pantallas.values().forEach { pantallas ->
                    NavigationBarItem(
                        icon = { /* Aquí podrías poner iconos según la pantalla */ },
                        label = { Text(pantallas.titulo)},
                        selected = currentRoute == pantallas.name,
                        onClick = {
                            navController.navigate(pantallas.name) {
                                // Evita acumular pantallas en la pila
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Contenedor de navegación (NavHost)
        NavHost(
            navController = navController,
            startDestination = Pantallas.Bienvenida.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Pantallas.Bienvenida.name) { 
                ScreenBienvenida(navController)
            }
            composable(Pantallas.Inscripcion.name) {
                ScreenInscripcion(onPersonaGuardada = {nuevaPersona ->
                    listaPersonas.add(nuevaPersona)
                    navController.navigate(Pantallas.Solicitudes.name)
                })
            }
            composable(Pantallas.Solicitudes.name) {
                ScreenSolicitudes(
                    listaPersonas = listaPersonas,
                    onDelete = { persona -> listaPersonas.remove(persona)}
                )
            }
            composable(Pantallas.Ayuda.name) {
                // ScreenAyuda()
            }
        }
    }
}
