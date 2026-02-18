package org.example.examen

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform