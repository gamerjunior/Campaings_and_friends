package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.ui.screen.HomeScreen

object Rutas{

    const val HOME = "home"
    const val DETALLEPERSONAJE = "detallePersonaje/{personajeID}"
    const val PERFIL = "perfil"
    const val DETALLECAMPANIA = "detalleCampania/{campaniaID}"
    const val DADOS = "dados"

    fun detalleCampaniaConId(campaniaID: Long) = "detalleCampania/$campaniaID"

    fun detallePersonajeConId(personajeID: Long) = "detallePersonaje/$personajeID"

}


fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
){
    val listacampanias = listOf(
        Campania(
            nombre = "Golpe de los dragones de oror",
            master = "pablo",
            Njugadores = 3,
            jugadores = arrayListOf("saul","carlos","josevi"),
            anotaciones = arrayListOf("nada","nada dos"),
            Nsesiones = 3,
            id = 1
        ),
        Campania(
            nombre = "Nunca brilla el sol",
            master = "saul",
            Njugadores = 5,
            jugadores = arrayListOf("jose","marcos","edu","mi padre","lola lolita"),
            anotaciones = arrayListOf("nada","nada dos"),
            Nsesiones = 8 ,
            id = 2
        ),
        Campania(
            nombre = "buscarle novia al carlos",
            master = "jose",
            Njugadores = 4,
            jugadores = arrayListOf("josevi","adrian","paco","abuelo carlos"),
            anotaciones = arrayListOf("nada","nada dos"),
            Nsesiones = 5,
            id = 3
        ),
        Campania(
            nombre = "la herencia de lord sebastian",
            master = "saul",
            Njugadores = 1,
            jugadores = arrayListOf("zaida"),
            anotaciones = arrayListOf("nada","nada dos"),
            Nsesiones = 3,
            id = 4
        )
    )

    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        composable(Rutas.HOME) {
            HomeScreen(
                modifier = Modifier,
                campanias = listacampanias
            )
        }
    }
}
