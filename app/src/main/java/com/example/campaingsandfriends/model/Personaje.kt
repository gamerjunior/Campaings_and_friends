package com.example.campaingsandfriends.model


data class Personaje(
    val id: Long,
    val nombre: String,
    val raza: RazaDnd,
    val subraza: SubrazaDnd? = null,
    val clase: ClaseDnd,
    val subclase: Subclase? = null,
    val nivel: Int = 1,
    val atributosBase: Atributos = Atributos(),
    val vida: PuntosDeVida,
    val competenciasHabilidades: Map<HabilidadDnd, TipoCompetencia> = emptyMap(),
    val slotsActuales: Map<Int, Int> = emptyMap(),
    val iniciativa: Int = 0,
) {
    //atributos del personaje(base+raza)
    val atributosFinales: Atributos
        get() = Atributos(
            fuerza = atributosBase.fuerza + raza.modificadoresAtributos.fuerza + (subraza?.modificadoresAtributos?.fuerza ?: 0),
            destreza = atributosBase.destreza + raza.modificadoresAtributos.destreza + (subraza?.modificadoresAtributos?.destreza ?: 0),
            constitucion = atributosBase.constitucion + raza.modificadoresAtributos.constitucion + (subraza?.modificadoresAtributos?.constitucion ?: 0),
            inteligencia = atributosBase.inteligencia + raza.modificadoresAtributos.inteligencia + (subraza?.modificadoresAtributos?.inteligencia ?: 0),
            sabiduria = atributosBase.sabiduria + raza.modificadoresAtributos.sabiduria + (subraza?.modificadoresAtributos?.sabiduria ?: 0),
            carisma = atributosBase.carisma + raza.modificadoresAtributos.carisma + (subraza?.modificadoresAtributos?.carisma ?: 0)
        )
    //bonificador de competencia por nivel
    val bonoCompetencia: Int
        get() = when (nivel) {
            in 1..4 -> 2
            in 5..8 -> 3
            in 9..12 -> 4
            in 13..16 -> 5
            in 17..20 -> 6
            else -> 2
        }



    //calcula el modificadoer de las habilidades
    fun calcularModificadorHabilidad(habilidad: HabilidadDnd): Int {
        // Obtener el modificador del atributo asociado
        val modAtributo = when (habilidad.atributoAsociado) {
            "FUE" -> atributosFinales.obtenerModificador(atributosFinales.fuerza)
            "DES" -> atributosFinales.obtenerModificador(atributosFinales.destreza)
            "CON" -> atributosFinales.obtenerModificador(atributosFinales.constitucion)
            "INT" -> atributosFinales.obtenerModificador(atributosFinales.inteligencia)
            "SAB" -> atributosFinales.obtenerModificador(atributosFinales.sabiduria)
            "CAR" -> atributosFinales.obtenerModificador(atributosFinales.carisma)
            else -> 0
        }

        // Obtener el grado de competencia
        val tipo = competenciasHabilidades[habilidad] ?: TipoCompetencia.NINGUNA

        // Sumar modificador + (bono * multiplicador)
        return modAtributo + (bonoCompetencia * tipo.multiplicador)
    }



    val velocidad: Int
        get() = raza.velocidadBase

    // nivel al que se desbloquea la subclase
    val nivelDesbloqueoSubclase: Int
        get() = when (clase) {
            ClaseDnd.MAGO -> 2
            ClaseDnd.GUERRERO -> 3
            // Puedes añadir el resto aquí
        }

    // comprueba si puedes elegir una subclase
    val debeElegirSubclase: Boolean
        get() = nivel >= nivelDesbloqueoSubclase && subclase == null
    // maximo de espacios por nivel
    val slotsMaximos: List<Int>
        get() {
            val deClase = clase.obtenerSlotsMaximosParaNivel(nivel)
            if (deClase.isNotEmpty()) return deClase

            return subclase?.tablaDeConjurosPropia?.find { it.nivelPersonaje == nivel }?.slotsMaximos ?: emptyList()
        }
    // comprueba si puedes usar magia
    val esLanzadorDeConjuros: Boolean
        get() = slotsMaximos.isNotEmpty()

    // lista de rasgos de la raza, la clase y de la subclase en el nivel actual
    val todosLosRasgos: List<RasgoClase>
        get() {
            val deRaza = raza.rasgosRaciales
            val deSubraza = subraza?.rasgosRaciales ?: emptyList()
            val deClase = clase.obtenerRasgosParaNivel(nivel)
            val deSubclase = subclase?.obtenerRasgosParaNivel(nivel) ?: emptyList()

            return (deRaza + deSubraza + deClase + deSubclase).sortedBy { it.nivelRequerido }
        }




}

//Los 6 atributos principales y sus modificadores
data class Atributos(
    val fuerza: Int = 8,
    val destreza: Int = 8,
    val constitucion: Int = 8,
    val inteligencia: Int = 8,
    val sabiduria: Int = 8,
    val carisma: Int = 8
) {
    // calcula el modificador (+1, -2, etc.)
    fun obtenerModificador(valor: Int): Int = (valor - 10) / 2
}

data class PuntosDeVida(
    val maximos: Int,
    val actuales: Int,
    val temporales: Int = 0
){
    override fun toString(): String {
        return "$actuales/$maximos"
    }
}
//cosas unicas de cada clase
data class RasgoClase(
    val nombre: String,
    val nivelRequerido: Int,
    val descripcion: String
)
//clases de dnd con sus rasgos
enum class ClaseDnd(
    val nombreVisible: String,
    val dadoDeGolpe: String, // ej. "d10", "d6"
    val rasgosPorNivel: List<RasgoClase>,
    val tablaDeConjuros: List<EspaciosPorNivel> = emptyList()
) {
    MAGO(
        nombreVisible = "Mago",
        dadoDeGolpe = "d6",
        rasgosPorNivel = listOf(
            RasgoClase("Recuperación Arcana", 1, "Recuperas algunos espacios de conjuro durante un descanso corto."),
            RasgoClase("Tradición Arcana", 2, "Elige tu escuela de magia (Evocación, Nigromancia, etc.)."),
            RasgoClase("Mejora de Característica", 4, "Aumenta un atributo en +2 o dos en +1."),
            RasgoClase("Versatilidad con Conjuros", 4, "Puedes cambiar un truco por otro de tu lista.")
        ),
        tablaDeConjuros = listOf(
            // En nivel 1
            EspaciosPorNivel(nivelPersonaje = 1, slotsMaximos = listOf(0, 2)),
            // En nivel 2
            EspaciosPorNivel(nivelPersonaje = 2, slotsMaximos = listOf(0, 3)),
            // En nivel 3
            EspaciosPorNivel(nivelPersonaje = 3, slotsMaximos = listOf(0, 4, 2)),
            // En nivel 4
            EspaciosPorNivel(nivelPersonaje = 4, slotsMaximos = listOf(0, 4, 3))

        )
    ),
    GUERRERO(
        nombreVisible = "Guerrero",
        dadoDeGolpe = "d10",
        rasgosPorNivel = listOf(
            RasgoClase("Estilo de Combate", 1, " Elige una especialización de combate (Defensa, Duelista, etc.)."),
            RasgoClase("Viento Renovado", 1, "Usa una acción de bonificación para recuperar 1d10 + nivel PG."),
            RasgoClase("Acción Súbita", 2, "Puedes realizar una acción adicional en tu turno."),
            RasgoClase("Arquetipo Marcial", 3, "Elige tu subclase (Campeón, Maestro de Batalla...).")
        )
    );

    //devuelve una lista con los slots disponibles para usar magia
    fun obtenerSlotsMaximosParaNivel(nivelActual: Int): List<Int> {
        return tablaDeConjuros.find { it.nivelPersonaje == nivelActual }?.slotsMaximos ?: emptyList()
    }
    //devuelve una lista con los rasgos de la clase en el nivel actual
    fun obtenerRasgosParaNivel(nivelActual: Int): List<RasgoClase> {
        return rasgosPorNivel.filter { it.nivelRequerido <= nivelActual }
    }
}

//clase que indica cuandtos espacios hay disponibles por cada nivel
data class EspaciosPorNivel(
    val nivelPersonaje: Int,
    val slotsMaximos: List<Int>
)

data class Subclase(
    val nombreId: String,
    val nombreVisible: String,
    val claseBase: ClaseDnd,
    val rasgosPorNivel: List<RasgoClase> = emptyList(),
    // si la subclase otorga magia a una clase que no la tiene
    val tablaDeConjurosPropia: List<EspaciosPorNivel> = emptyList()
) {
    fun obtenerRasgosParaNivel(nivelActual: Int): List<RasgoClase> {
        return rasgosPorNivel.filter { it.nivelRequerido <= nivelActual }
    }
}
//lista de subclases
object CatalogoSubclases {
    val EVOCACION = Subclase(
        nombreId = "EVOCACION",
        nombreVisible = "Escuela de Evocación",
        claseBase = ClaseDnd.MAGO,
        rasgosPorNivel = listOf(
            RasgoClase("Evocación Erudita", 2, "El oro y tiempo que gastas en copiar conjuros de evocación se reduce a la mitad."),
            RasgoClase("Esculpir Conjuros", 2, "Puedes crear zonas seguras para tus aliados dentro de tus hechizos de área (como Bola de Fuego).")
        )
    )

    val CABALLERO_MISTICO = Subclase(
        nombreId = "CABALLERO_MISTICO",
        nombreVisible = "Caballero Místico",
        claseBase = ClaseDnd.GUERRERO,
        rasgosPorNivel = listOf(
            RasgoClase("Lanzamiento de Conjuros", 3, "Obtienes la capacidad de lanzar conjuros de mago."),
            RasgoClase("Vínculo con el Arma", 3, "Puedes crear un vínculo mágico con hasta dos armas mediante un ritual.")
        ),
        // Esta subclase rompe la regla e inyecta magia al guerrero a partir de nivel 3:
        tablaDeConjurosPropia = listOf(
            EspaciosPorNivel(nivelPersonaje = 3, slotsMaximos = listOf(0, 2)),
            EspaciosPorNivel(nivelPersonaje = 4, slotsMaximos = listOf(0, 3))
        )
    )

    // Función para obtener las subclases válidas de una clase específica
    fun obtenerSubclasesPara(clase: ClaseDnd): List<Subclase> {
        return listOf(EVOCACION, CABALLERO_MISTICO).filter { it.claseBase == clase }
    }
}

//clase de las razas
data class RazaDnd(
    val nombreId: String,
    val nombreVisible: String,
    val velocidadBase: Int = 30,   // Velocidad en pies
    val modificadoresAtributos: AtributosModificadores,
    val rasgosRaciales: List<RasgoClase> = emptyList()
)

// Una clase auxiliar ligera para definir los bonus de atributo de la raza
data class AtributosModificadores(
    val fuerza: Int = 0,
    val destreza: Int = 0,
    val constitucion: Int = 0,
    val inteligencia: Int = 0,
    val sabiduria: Int = 0,
    val carisma: Int = 0
)

object CatalogoRazas {
    val ENANO = RazaDnd(
        nombreId = "ENANO",
        nombreVisible = "Enano",
        velocidadBase = 25,
        modificadoresAtributos = AtributosModificadores(
            constitucion = 2
        ),
        rasgosRaciales = listOf(
            RasgoClase("Visión en la Oscuridad", 1, "Puedes ver en la penumbra a una distancia de 60 pies como si fuera luz brillante."),
            RasgoClase("Resistencia Enana", 1, "Tienes ventaja en las tiradas de salvación contra veneno y resistencia contra el daño de veneno."),
            RasgoClase("Entrenamiento en Combate Enano", 1, "Tienes competencia con el hacha de batalla, el hacha de mano, el martillo ligero y el martillo de guerra.")
        )
    )
    val HUMANO = RazaDnd(
        nombreId = "HUMANO",
        nombreVisible = "Humano",
        velocidadBase = 30,
        modificadoresAtributos = AtributosModificadores(
            fuerza = 1, destreza = 1, constitucion = 1,
            inteligencia = 1, sabiduria = 1, carisma = 1
        ),
        rasgosRaciales = listOf(
            RasgoClase("Versatilidad Humana", 1, "Los humanos ganan un aumento general en todas sus capacidades físicas y mentales.")
        )
    )

    val ELFO = RazaDnd(
        nombreId = "ELFO",
        nombreVisible = "Elfo",
        velocidadBase = 30,
        modificadoresAtributos = AtributosModificadores(destreza = 2),
        rasgosRaciales = listOf(
            RasgoClase("Visión en la Oscuridad", 1, "Puedes ver en la penumbra a una distancia de 60 pies como si fuera luz brillante."),
            RasgoClase("Sentidos Agudos", 1, "Tienes competencia en la habilidad de Percepción."),
            RasgoClase("Linaje Feérico", 1, "Tienes ventaja en las tiradas de salvación para no quedar hechizado y la magia no puede dormirte.")
        )
    )
}

data class SubrazaDnd(
    val nombreId: String,
    val nombreVisible: String,
    val razaBaseId: String,
    val modificadoresAtributos: AtributosModificadores = AtributosModificadores(),
    val rasgosRaciales: List<RasgoClase> = emptyList()
)

object CatalogoSubrazas {
    val ALTO_ELFO = SubrazaDnd(
        nombreId = "ALTO_ELFO",
        nombreVisible = "Alto Elfo",
        razaBaseId = "ELFO",
        modificadoresAtributos = AtributosModificadores(inteligencia = 1),
        rasgosRaciales = listOf(
            RasgoClase("Entrenamiento en Armas Élficas", 1, "Tienes competencia con la espada larga, espada corta, arco corto y arco largo."),
            RasgoClase("Truco extra", 1, "Conoces un truco de la lista de conjuros de mago.")
        )
    )

    val ENANO_MONTAÑA = SubrazaDnd(
        nombreId = "ENANO_MONTAÑA",
        nombreVisible = "Enano de las Montañas",
        razaBaseId = "ENANO",
        modificadoresAtributos = AtributosModificadores(fuerza = 2),
        rasgosRaciales = listOf(
            RasgoClase("Entrenamiento con Armadura Enana", 1, "Tienes competencia con armaduras ligeras y medias.")
        )
    )

    // Obtener las subrazas válidas para una raza concreta
    fun obtenerSubrazasPara(razaId: String): List<SubrazaDnd> {
        return listOf(ALTO_ELFO, ENANO_MONTAÑA).filter { it.razaBaseId == razaId }
    }
}


enum class HabilidadDnd(val nombreVisible: String, val atributoAsociado: String) {
    ATLETISMO("Atletismo", "FUE"),
    ACROBACIAS("Acrobacias", "DES"),
    JUEGO_DE_MANOS("Juego de Manos", "DES"),
    SIGILO("Sigilo", "DES"),
    ARCANOS("Arcanos", "INT"),
    HISTORIA("Historia", "INT"),
    INVESTIGACION("Investigación", "INT"),
    NATURALEZA("Naturaleza", "INT"),
    RELIGION("Religión", "INT"),
    PERCEPCION_PASIVA("Percepción Pasiva", "SAB"), // Útil para tenerla mapeada
    PERCEPCION("Percepción", "SAB"),
    PERSPICACIA("Perspicacia", "SAB"),
    MEDICINA("Medicina", "SAB"),
    SUPERVIVENCIA("Supervivencia", "SAB"),
    TRATO_ANIMALES("Trato con Animales", "SAB"),
    ENGAÑO("Engaño", "CAR"),
    INTIMIDACION("Intimidación", "CAR"),
    INTERPRETACION("Interpretación", "CAR"),
    PERSUASION("Persuasión", "CAR")
}


enum class TipoCompetencia(val multiplicador: Int) {
    NINGUNA(0),
    COMPETENTE(1),
    PERICIA(2) // Doble competencia (Expertise)
}

