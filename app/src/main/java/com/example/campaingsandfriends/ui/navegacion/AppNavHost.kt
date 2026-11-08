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
import com.example.campaingsandfriends.model.CatalogoRazas
import com.example.campaingsandfriends.model.ClaseDnd
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.model.PuntosDeVida
import com.example.campaingsandfriends.ui.screen.CampaniaFormScreen
import com.example.campaingsandfriends.ui.screen.DadoScreen
import com.example.campaingsandfriends.ui.screen.DetalleCampaniaScreen
import com.example.campaingsandfriends.ui.screen.DetallePersonajeScreen
import com.example.campaingsandfriends.ui.screen.HomeScreen
import com.example.campaingsandfriends.ui.screen.PerfilScreen
import com.example.campaingsandfriends.ui.screen.PersonajeFormScreen
import com.example.campaingsandfriends.ui.screen.PersonajesScreen

object Rutas{

    const val HOME = "home"
    const val DETALLEPERSONAJE = "detallePersonaje/{personajeId}"
    const val PERFIL = "perfil"
    const val DETALLECAMPANIA = "detalleCampania/{campaniaId}"
    const val DADOS = "dados"
    const val PERSONAJES = "personajes"

    const val NUEVA_CAMPANIA = "nueva-campania"
    const val EDITAR_CAMPANIA = "editar-campania/{campaniaId}"

    const val NUEVO_PERSONAJE = "nuevo-personaje"
    const val EDITAR_PERSONAJE = "editar-personaje/{personajeId}"

    fun detalleCampaniaConId(campaniaId: Long) = "detalleCampania/$campaniaId"

    fun detallePersonajeConId(personajeId: Long) = "detallePersonaje/$personajeId"

    fun formularioCampaniaConId(campaniaId: Long) = "editar-campania/$campaniaId"

    fun formularioPersonajeConId(personajeId: Long) = "editar-personaje/$personajeId"
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
            nombre = "saúl guerrero",
            raza = CatalogoRazas.HUMANO,
            clase = ClaseDnd.GUERRERO,
            nivel = 10,
            vida = PuntosDeVida(
                maximos = 10,
                actuales = 10,
                temporales = 0
            ),
            id = 1
        ),
        Personaje(
            nombre = "saúl mago",
            raza = CatalogoRazas.HUMANO,
            clase = ClaseDnd.MAGO,
            nivel = 10,
            vida = PuntosDeVida(
                maximos = 10,
                actuales = 10,
                temporales = 0
            ),
            id = 2,
            slotsActuales = mapOf(1 to 4, 2 to 3, 3 to 3, 4 to 3, 5 to 2)
        ),
        Personaje(
            nombre = "saúl enano",
            raza = CatalogoRazas.ENANO,
            clase = ClaseDnd.GUERRERO,
            nivel = 10,
            vida = PuntosDeVida(
                maximos = 10,
                actuales = 10,
                temporales = 0
            ),
            id = 3
        ),

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
                onPersonajeClick = { navController.navigate(Rutas.detallePersonajeConId(it)) },
                onNuevoPersonajeClick = { navController.navigate(Rutas.NUEVO_PERSONAJE) }
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

        composable(Rutas.NUEVO_PERSONAJE) {
            PersonajeFormScreen(
                modifier = Modifier,
                personajeInicial = null,
                onSaveClick = { nuevoPersonaje ->
                    listapersonajes.add(nuevoPersonaje)
                    navController.popBackStack()
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        composable(
            Rutas.EDITAR_PERSONAJE,
            arguments = listOf(navArgument("personajeId") { type = NavType.LongType })
        ) { arg ->
            PersonajeFormScreen(
                modifier = Modifier,
                personajeInicial = listapersonajes.find { it.id == arg.arguments?.getLong("personajeId") },
                onSaveClick = { personajeActualizado ->
                    val indice = listapersonajes.indexOfFirst { it.id == personajeActualizado.id }
                    if (indice != -1) {
                        listapersonajes[indice] = personajeActualizado
                    }
                    navController.popBackStack()
                },
                onCancelarClick = { navController.popBackStack() }
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