package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.ui.screen.CampaniaFormScreen
import com.example.campaingsandfriends.ui.screen.DadoScreen
import com.example.campaingsandfriends.ui.screen.DetalleCampaniaScreen
import com.example.campaingsandfriends.ui.screen.DetallePersonajeScreen
import com.example.campaingsandfriends.ui.screen.HomeScreen
import com.example.campaingsandfriends.ui.screen.PersonajesScreen
import com.example.campaingsandfriends.ui.screen.PerfilScreen

object Rutas{

    const val HOME = "home"
    const val DETALLEPERSONAJE = "detallePersonaje/{personajeId}"
    const val PERFIL = "perfil"
    const val DETALLECAMPANIA = "detalleCampania/{campaniaId}"
    const val DADOS = "dados"
    const val PERSONAJES = "personajes"

    const val NUEVA_CAMPANIA = "nueva-campania"

    const val EDITAR_CAMPANIA = "editar-campania/{campaniaId}"

    fun detalleCampaniaConId(campaniaId: Long) = "detalleCampania/$campaniaId"

    fun detallePersonajeConId(personajeId: Long) = "detallePersonaje/$personajeId"

    fun formularioCampaniaConId(campaniaId: Long) = "editar-campania/$campaniaId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    onPersonajeCargado: (String) -> Unit
){
    val listacampanias = remember { mutableListOf(
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
    }

    val listapersonajes = remember { mutableListOf(
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
        ),
        Personaje(
            nombre = "saúl",
            raza = "humano",
            clase = "guerrero",
            nivel = 10,
            vida = 4,
            id = 3
        ),
        Personaje(
            nombre = "brudom",
            raza = "draconido",
            clase = "explorador",
            nivel = 6,
            vida = 10,
            id = 4
        )

    ) }

    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        composable(Rutas.HOME) {
            HomeScreen(
                modifier = Modifier,
                campanias = listacampanias,
                onCampaniaListClick = {
                    navController.navigate(Rutas.detalleCampaniaConId(it))
                },
                onNuevaCampaniaClick = {navController.navigate(Rutas.NUEVA_CAMPANIA)}
            )
        }
        composable(Rutas.DETALLECAMPANIA,
            arguments = listOf(navArgument("campaniaId") { type = NavType.LongType })
        ) { arg ->
            DetalleCampaniaScreen(
                modifier = Modifier,
                campania = listacampanias.find { it.id == arg.arguments?.getLong("campaniaId") }!!
            )
        }

        composable(Rutas.PERSONAJES) {
            PersonajesScreen(
                modifier = Modifier,
                personajes = listapersonajes,
                onPersonajeClick = { navController.navigate(Rutas.detallePersonajeConId(it))
                }
            )
        }

        composable(Rutas.DADOS) {
            DadoScreen(
                modifier = Modifier
            )
        }
        composable(
            route = Rutas.DETALLEPERSONAJE,
            arguments = listOf(navArgument("personajeId") { type = NavType.LongType })
        ) { arg ->
            DetallePersonajeScreen(
                modifier = Modifier,
                personaje = listapersonajes.find { it.id == arg.arguments?.getLong("personajeId")} !!,
                onPersonajeCargado = onPersonajeCargado
            )
        }
        composable(Rutas.PERFIL) {
            PerfilScreen(
                modifier = Modifier
            )
        }

        composable(Rutas.NUEVA_CAMPANIA){
            CampaniaFormScreen(
                modifier = Modifier,
                campaniaInicial = null,
                onSaveClick = {
                    listacampanias.add(it)
                    navController.popBackStack()
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        composable(
            Rutas.EDITAR_CAMPANIA,
            arguments = listOf(navArgument("campaniaId") { type = NavType.LongType })
        ){ arg ->
            CampaniaFormScreen(
                modifier = Modifier,
                onSaveClick = { campaniaActualizada ->
                    val indice = listacampanias.indexOfFirst { it.id == campaniaActualizada.id }

                    if (indice != -1) {
                        listacampanias[indice] = campaniaActualizada
                    }

                    navController.popBackStack()
                },
                onCancelarClick = { navController.popBackStack() },
                campaniaInicial = listacampanias.find { it.id == arg.arguments?.getLong("campaniaId") } !!
            )

        }
    }
}
// para mas adelante, en el view model
//fun buscarPersonaje(id: Long) : Personaje{
//
//
//    return listapersonajes.find { it.id == id }!!
//}
//
//fun buscarCampania(id: Long) : Campania{
//
//
//    return listacampanias.find { it.id == id }!!
//}