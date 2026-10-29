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

}