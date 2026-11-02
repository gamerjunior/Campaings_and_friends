package com.example.campaingsandfriends.validacion

object CampaniaFormValidator {
    fun validarNombre(nombre: String): String? {
        return if (nombre.isBlank()) {
            "El nombre es obligatorio"
        } else { null }
    }

    fun validarMaster(master: String): String? {
        return if (master.isBlank()) {
            "El master es obligatorio"
        } else { null }
    }

    fun validarNjugadores(Njugadores: String): String? {
        val numero = Njugadores.toIntOrNull()
        return when{
            numero == null -> "necesitas minimo un jugador"
            numero <= 0 -> "No puedes jugar con personas negativas"
            else -> null
        }
    }


}