package com.example.campaingsandfriends.validacion

object PersonajeFormValidator {
    fun validarNombre(nombre: String): String? {
        return if (nombre.isBlank()) {
            "El nombre es obligatorio"
        } else { null }
    }

    fun validarNivel(nivel: String): String? {
        val num = nivel.toIntOrNull()
        return when {
            num == null -> "El nivel debe ser un número"
            num < 1 || num > 20 -> "El nivel debe estar entre 1 y 20"
            else -> null
        }
    }

    fun validarAtributo(valor: String, nombre: String): String? {
        val num = valor.toIntOrNull()
        return when {
            num == null -> "El valor de $nombre debe ser un número"
            num < 1 || num > 30 -> "$nombre debe estar entre 1 y 30"
            else -> null
        }
    }
}
