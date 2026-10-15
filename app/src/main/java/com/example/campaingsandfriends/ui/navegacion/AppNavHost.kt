package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.ui.screen.DadoScreen
import com.example.campaingsandfriends.ui.screen.DetalleCampaniaScreen
import com.example.campaingsandfriends.ui.screen.DetallePersonajeScreen
import com.example.campaingsandfriends.ui.screen.HomeScreen
import com.example.campaingsandfriends.ui.screen.PerfilScreen

object Rutas{

    const val HOME = "home"
    const val DETALLEPERSONAJE = "detallePersonaje/{personajeID}"
    const val PERFIL = "perfil"
    const val DETALLECAMPANIA = "detalleCampania/{campaniaID}"
    const val DADOS = "dados"

    fun detalleCampaniaConId(campaniaID: Long) = "detalleCampania/$campaniaID"

    fun detallePersonajeConId(personajeID: Long) = "detallePersonaje/$personajeID"

}


@Composable
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
                modifier = modifier,
                campanias = listacampanias,
                onCampaniaClick = {navController.navigate(Rutas.detalleCampaniaConId(it))
                }
            )
        }
        composable(Rutas.DETALLECAMPANIA,
            arguments = listOf(navArgument("campaniaId") { type = NavType.LongType })
        ) {
            DetalleCampaniaScreen(
                modifier = modifier,
                campania = buscarCampania(it.arguments?.getLong("campaniaId")!!)
            )
        }

        composable(Rutas.PERFIL) {
            PerfilScreen(
                modifier = modifier,
                onPersonajeClick = { navController.navigate(Rutas.detallePersonajeConId(it))
                }
            )
        }

        composable(Rutas.DADOS) {
            DadoScreen(
                modifier = modifier
            )
        }
        composable(Rutas.DETALLEPERSONAJE,
            arguments = listOf(navArgument("personajeId") { type = NavType.LongType })
        ) {
            DetallePersonajeScreen(
                modifier = modifier,
                personaje = buscarPersonaje(it.arguments?.getLong("personajeId")!!)
            )
        }
    }
}

fun buscarPersonaje(id: Long) : Personaje{

    val listapersonajes = listOf(
        Personaje(
            nombre = "idril",
            raza = "semielfo",
            clase = "picaro",
            nivel = 2,
            vida = 14,
            id = 1
        ),
        Personaje(
            nombre = "manolo",
            raza = "orco",
            clase = "guerrero",
            nivel = 2,
            vida = 20,
            id = 2
        )

    )
    return listapersonajes.find { it.id == id }!!
}

fun buscarCampania(id: Long) : Campania{

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
    return listacampanias.find { it.id == id }!!
}