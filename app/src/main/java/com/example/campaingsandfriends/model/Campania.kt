package com.example.campaingsandfriends.model

data class Campania(
    val nombre: String,
    val master: String,
    val Njugadores: Int,
    val jugadores: ArrayList<String>,
    val anotaciones: ArrayList<String>,
    val Nsesiones: Int
    )