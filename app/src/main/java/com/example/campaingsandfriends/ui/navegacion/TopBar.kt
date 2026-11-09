package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.campaingsandfriends.R
import androidx.compose.ui.graphics.Color
import com.example.campaingsandfriends.model.Personaje

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    nombrePersonaje: String,
    nombreCamapania: String
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val personajeId = navBackStackEntry?.arguments?.getLong("personajeId")
    val campaniaId = navBackStackEntry?.arguments?.getLong("campaniaId")
    TopAppBar(
        title = {
            if (currentRoute == Rutas.HOME) {
                Text(text = "Home")
            } else if (currentRoute == Rutas.DETALLECAMPANIA) {
                Text(text = "Detalle de la campaña")
            } else if (currentRoute == Rutas.DETALLEPERSONAJE) {
                Text(text = nombrePersonaje.ifEmpty { "Detalle del personaje" })
            } else if (currentRoute == Rutas.PERFIL) {
                Text(text = "Perfil")
            } else if (currentRoute == Rutas.DADOS) {
                Text(text = "Dados")
            } else if (currentRoute == Rutas.PERSONAJES) {
                Text(text = "Personajes")
            }else if (currentRoute == Rutas.NUEVA_CAMPANIA) {
                Text(text= "Nueva Campaña")
            }else if(currentRoute == Rutas.EDITAR_CAMPANIA){
                Text(text = nombreCamapania.ifEmpty { "Editar Campania" } )
            }else if (currentRoute == Rutas.NUEVO_PERSONAJE) {
                Text(text= "Nuevo Personaje")
            }else if(currentRoute == Rutas.EDITAR_PERSONAJE){
                Text(text = nombrePersonaje.ifEmpty { "Editar Personaje" } )
            }
            else {
                Text(text = "")
            }
        }, navigationIcon = {
            if (currentRoute != Rutas.HOME) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "back"
                    )
                }
            }

        }, actions = {
            if (currentRoute == Rutas.DETALLECAMPANIA && campaniaId != null){
                IconButton(onClick = { navController.navigate(Rutas.formularioCampaniaConId(campaniaId)) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "edit"
                    )
                }
            }
            if (currentRoute == Rutas.DETALLEPERSONAJE && personajeId != null){
                IconButton(onClick = { navController.navigate(Rutas.formularioPersonajeConId(personajeId)) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "edit"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6B529A),
            titleContentColor = Color.Black)
    )

}

