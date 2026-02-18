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
        Text("\nEsta aplicación busca facilitarte la búsqueda de trabajo mediante la presentación de un minicurriculo. Buscar trabajo supone asumir un proceso activo, estructurado y exigente que requiere planificación, disciplina y estrategia, más que una simple búsqueda pasiva.  No se trata solo de enviar currículos, sino de gestionar una \"carrera profesional\" personal, entendiendo que es un trabajo en sí mismo. \n" +
                "Buscar trabajo implica dedicar tiempo diario, establecer metas, mantener un horario y desarrollar hábitos como investigar empresas, redactar currículos personalizados y preparar entrevistas. \n" +
                "El proceso incluye múltiples pasos: desde autoevaluarse (qué te gusta, qué habilidades tienes), localizar ofertas (a través de portales como Indeed, LinkedIn, o redes personales), enviar candidaturas, prepararse para entrevistas y mantener una red activa.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { uriHandler.openUri("https://www.sepe.es/HomeSepe/es/") }) {
            Text("Ir al SEPE")
        }
    }
}