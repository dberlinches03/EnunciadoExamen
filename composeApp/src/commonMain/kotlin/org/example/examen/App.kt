package org.example.examen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
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
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource

import enunciadoexamen.composeapp.generated.resources.Res
import enunciadoexamen.composeapp.generated.resources.logoE
import org.example.examen.ui.screen.Pantallas
import org.example.examen.ui.theme.BlueDeep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppEmpleo() {
    val navController = rememberNavController()

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
            NavigationBar {

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
                // ScreenBienvenida(navController) 
            }
            composable(Pantallas.Inscripcion.name) { /* Punto 2 */ }
            composable(Pantallas.Solicitudes.name) { /* Punto 3 */ }
            composable(Pantallas.Ayuda.name) { /* Punto 5 */ }
        }
    }
}
