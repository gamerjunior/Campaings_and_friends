package com.example.campaingsandfriends.validacion

import com.example.campaingsandfriends.model.CatalogoRazas
import com.example.campaingsandfriends.model.CatalogoSubclases
import com.example.campaingsandfriends.model.CatalogoSubrazas
import com.example.campaingsandfriends.model.ClaseDnd
import com.example.campaingsandfriends.model.RazaDnd
import com.example.campaingsandfriends.model.Subclase
import com.example.campaingsandfriends.model.SubrazaDnd

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

    fun validarClase(clase: ClaseDnd): String? {
        return when {
            clase == ClaseDnd.VACIO -> "Selecciona una clase"
            else -> null
        }
    }

    fun validarRaza(raza: RazaDnd): String? {
        return when {
            raza == CatalogoRazas.VACIO || raza.nombreId == "VACIO" -> "Selecciona una raza"
            else -> null
        }
    }

    fun validarSubraza(raza: RazaDnd, subraza: SubrazaDnd?): String? {
        val subrazasDisponibles = CatalogoSubrazas.obtenerSubrazasPara(raza.nombreId)
        return when {
            subrazasDisponibles.isNotEmpty() && subraza == null -> "Selecciona una subraza"
            else -> null
        }
    }

    fun validarSubclase(clase: ClaseDnd, nivel: Int, subclase: Subclase?): String? {
        val subclasesDisponibles = CatalogoSubclases.obtenerSubclasesPara(clase)
        val puedeElegir = clase != ClaseDnd.VACIO && nivel >= clase.nivelDesbloqueoSubclase
        return when {
            puedeElegir && subclasesDisponibles.isNotEmpty() && subclase == null -> "Selecciona una subclase"
            else -> null
        }
    }
}
