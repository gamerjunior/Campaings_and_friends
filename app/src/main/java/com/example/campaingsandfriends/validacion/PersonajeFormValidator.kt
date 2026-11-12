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
            num !in 1..20 -> "El nivel debe estar entre 1 y 20"
            else -> null
        }
    }

    fun validarHabilidades(seleccionadas: Int, requeridas: Int = 2): String? {
        return when {
            seleccionadas < requeridas -> "Selecciona al menos $requeridas habilidades"
            else -> null
        }
    }
}
