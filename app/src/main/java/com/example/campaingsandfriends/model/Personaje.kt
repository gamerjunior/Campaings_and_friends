package com.example.campaingsandfriends.model

data class Personaje (
    val nombre: String,
    val raza: String,
    val clase: Clase,
    val nivel: Int,
    val vida: Int,
    val id: Long
)

data class AtributosClase(
    val id: String,
    val nombre: String,
    val dadoGolpe: String,
    val caracteristicaPrincipal: String,
    val tiradasSalvacion: List<String>,
    val competenciasArmadura: List<String>,
    val competenciasArmas: List<String>,
    val competenciasHerramientas: List<String>,
    val descripcion: String
)

object Clase {

    const val BARBARO = "barbaro"
    const val BARDO = "bardo"
    const val BRUJO = "brujo"
    const val CLERIGO = "clerigo"
    const val DRUIDA = "druida"
    const val EXPLORADOR = "explorador"
    const val GUERRERO = "guerrero"
    const val HECHIZERO = "hechizero"
    const val MAGO = "mago"
    const val MONJE = "monje"
    const val PALADIN = "paladin"
    const val PICARO = "picaro"

    val BARBARO_INFO = AtributosClase(
        id = BARBARO,
        nombre = "Bárbaro",
        dadoGolpe = "d12",
        caracteristicaPrincipal = "Fuerza",
        tiradasSalvacion = mutableListOf("Fuerza", "Constitución"),
        competenciasArmadura = mutableListOf("Armaduras ligeras", "Armaduras medianas", "Escudos"),
        competenciasArmas = mutableListOf("Armas sencillas", "Armas marciales"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un fiero guerrero de trasfondo primitivo que puede entrar en una furia de combate."
    )

    val BARDO_INFO = AtributosClase(
        id = BARDO,
        nombre = "Bardo",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Carisma",
        tiradasSalvacion = mutableListOf("Destreza", "Carisma"),
        competenciasArmadura = mutableListOf("Armaduras ligeras"),
        competenciasArmas = mutableListOf("Armas sencillas", "Ballestas de mano", "Espadas cortas", "Estoques", "Espadas largas"),
        competenciasHerramientas = mutableListOf("Tres instrumentos musicales a tu elección"),
        descripcion = "Un mago inspirador cuyo poder resuena en la música, la palabra y la magia."
    )

    val BRUJO_INFO = AtributosClase(
        id = BRUJO,
        nombre = "Brujo",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Carisma",
        tiradasSalvacion = mutableListOf("Sabiduría", "Carisma"),
        competenciasArmadura = mutableListOf("Armaduras ligeras"),
        competenciasArmas = mutableListOf("Armas sencillas"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un combatiente que deriva su poder mágico de un pacto con una entidad extraplanar."
    )

    val CLERIGO_INFO = AtributosClase(
        id = CLERIGO,
        nombre = "Clérigo",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Sabiduría",
        tiradasSalvacion = mutableListOf("Sabiduría", "Carisma"),
        competenciasArmadura = mutableListOf("Armaduras ligeras", "Armaduras medianas", "Escudos"),
        competenciasArmas = mutableListOf("Armas sencillas"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un campeón sacerdotal que esgrime magia divina al servicio de un poder superior."
    )

    val DRUIDA_INFO = AtributosClase(
        id = DRUIDA,
        nombre = "Druida",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Sabiduría",
        tiradasSalvacion = mutableListOf("Inteligencia", "Sabiduría"),
        competenciasArmadura = mutableListOf("Armaduras ligeras", "Armaduras medianas", "Escudos (no metálicos)"),
        competenciasArmas = mutableListOf("Bastones", "Dagas", "Dardos", "Hoces", "Jabalinas", "Mazas", "Orbes", "Picas", "Hondas", "Lanzas"),
        competenciasHerramientas = mutableListOf("Estuche de herbalismo"),
        descripcion = "Un sacerdote de la Antigua Fe que adopta formas de bestias y canaliza las fuerzas de la naturaleza."
    )

    val EXPLORADOR_INFO = AtributosClase(
        id = EXPLORADOR,
        nombre = "Explorador",
        dadoGolpe = "d10",
        caracteristicaPrincipal = "Destreza y Sabiduría",
        tiradasSalvacion = mutableListOf("Fuerza", "Destreza"),
        competenciasArmadura = mutableListOf("Armaduras ligeras", "Armaduras medianas", "Escudos"),
        competenciasArmas = mutableListOf("Armas sencillas", "Armas marciales"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un maestro del combate silvestre y el rastreo que utiliza magia de la naturaleza."
    )

    val GUERRERO_INFO = AtributosClase(
        id = GUERRERO,
        nombre = "Guerrero",
        dadoGolpe = "d10",
        caracteristicaPrincipal = "Fuerza o Destreza",
        tiradasSalvacion = mutableListOf("Fuerza", "Constitución"),
        competenciasArmadura = mutableListOf("Todas las armaduras", "Escudos"),
        competenciasArmas = mutableListOf("Armas sencillas", "Armas marciales"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un maestro del combate marcial, experto con una gran variedad de armas y armaduras."
    )

    val HECHIZERO_INFO = AtributosClase(
        id = HECHIZERO,
        nombre = "Hechicero",
        dadoGolpe = "d6",
        caracteristicaPrincipal = "Carisma",
        tiradasSalvacion = mutableListOf("Constitución", "Carisma"),
        competenciasArmadura = emptyList(),
        competenciasArmas = mutableListOf("Dagas", "Dardos", "Hondas", "Bastones", "Ballestas ligeras"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un lanzador de conjuros que saca su magia inherente de un don o linaje ancestral."
    )

    val MAGO_INFO = AtributosClase(
        id = MAGO,
        nombre = "Mago",
        dadoGolpe = "d6",
        caracteristicaPrincipal = "Inteligencia",
        tiradasSalvacion = mutableListOf("Inteligencia", "Sabiduría"),
        competenciasArmadura = emptyList(),
        competenciasArmas = mutableListOf("Dagas", "Dardos", "Hondas", "Bastones", "Ballestas ligeras"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un usuario de la magia escolar capaz de manipular las estructuras de la realidad."
    )

    val MONJE_INFO = AtributosClase(
        id = MONJE,
        nombre = "Monje",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Destreza y Sabiduría",
        tiradasSalvacion = mutableListOf("Fuerza", "Destreza"),
        competenciasArmadura = emptyList(),
        competenciasArmas = mutableListOf("Armas sencillas", "Espadas cortas"),
        competenciasHerramientas = mutableListOf("Herramientas de artesano o instrumento musical"),
        descripcion = "Un maestro de las artes marciales que utiliza el poder de su ki para canalizar habilidades sobrehumanas."
    )

    val PALADIN_INFO = AtributosClase(
        id = PALADIN,
        nombre = "Paladín",
        dadoGolpe = "d10",
        caracteristicaPrincipal = "Fuerza y Carisma",
        tiradasSalvacion = mutableListOf("Sabiduría", "Carisma"),
        competenciasArmadura = mutableListOf("Todas las armaduras", "Escudos"),
        competenciasArmas = mutableListOf("Armas sencillas", "Armas marciales"),
        competenciasHerramientas = emptyList(),
        descripcion = "Un guerrero santo juramentado a un voto sagrado que combina la lucha marcial con la magia divina."
    )

    val PICARO_INFO = AtributosClase(
        id = PICARO,
        nombre = "Pícaro",
        dadoGolpe = "d8",
        caracteristicaPrincipal = "Destreza",
        tiradasSalvacion = mutableListOf("Destreza", "Inteligencia"),
        competenciasArmadura = mutableListOf("Armaduras ligeras"),
        competenciasArmas = mutableListOf("Armas sencillas", "Ballestas de mano", "Estoques", "Espadas cortas", "Espadas largas"),
        competenciasHerramientas = mutableListOf("Herramientas de ladrón"),
        descripcion = "Un especialista del sigilo, el engaño y la astucia capaz de superar casi cualquier obstáculo."
    )

    val ATRIBUTOS: Map<String, AtributosClase> = mapOf(
        BARBARO to BARBARO_INFO,
        BARDO to BARDO_INFO,
        BRUJO to BRUJO_INFO,
        CLERIGO to CLERIGO_INFO,
        DRUIDA to DRUIDA_INFO,
        EXPLORADOR to EXPLORADOR_INFO,
        GUERRERO to GUERRERO_INFO,
        HECHIZERO to HECHIZERO_INFO,
        MAGO to MAGO_INFO,
        MONJE to MONJE_INFO,
        PALADIN to PALADIN_INFO,
        PICARO to PICARO_INFO
    )

    fun obtenerAtributos(idClase: String): AtributosClase? {
        return ATRIBUTOS[idClase.lowercase()]
    }

    fun getListaClases(): List<String> {
        return listOf(
            BARBARO, BARDO, BRUJO, CLERIGO, DRUIDA,
            EXPLORADOR, GUERRERO, HECHIZERO, MAGO, MONJE,
            PALADIN, PICARO
        )
    }

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
