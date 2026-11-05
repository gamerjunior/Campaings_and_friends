package com.example.campaingsandfriends.model

data class Personaje (
    val nombre: String,
    val raza: String,
    val clase: String,
    val nivel: Int,
    val vida: Int,
    val id: Long
)

object Clase{

    const val BARBARO = "barbaro"

    const val BARDO = "bardo"

    const val BRUJO= "brujo"

    const val CLERIGO = "clerigo"

    const val DRUIDA = "druida"

    const val EXPLORADOR = "explorador"

    const val GUERRERO = "guerrero"

    const val HECHIZERO = "hechizero"

    const val MAGO = "mago"

    const val MONJE = "monje"

    const val PALADIN = "paladin"

    const val PICARO = "picaro"
}

object Raza{
    const val HUMANO = "humano"
    const val ELFO = "elfo"
    const val ENANO = "enano"
    const val SEMIORCO = "semiorco"
    const val MEDIANO = "mediano"
    const val DRACONIDO = "draconido"
    const val TIEFLINGO = "tieflin"
    const val SEMIELFO = "semielfo"
    const val GNOMO = "gnomo"
}