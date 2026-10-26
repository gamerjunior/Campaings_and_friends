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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController

){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            if (currentRoute == Rutas.HOME) {
                Text(text = "Home")
            } else if (currentRoute == Rutas.DETALLECAMPANIA) {
                Text(text = "Detalle de la campaña")
            } else if (currentRoute == Rutas.DETALLEPERSONAJE) {
                Text(text = "")
            } else if (currentRoute == Rutas.PERFIL) {
                Text(text = "Perfil")
            } else if (currentRoute == Rutas.DADOS) {
                Text(text = "Dados")
            } else if (currentRoute == Rutas.PERSONAJES) {
                Text(text = "Personajes")
            } else {
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
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF94e1bc),
            titleContentColor = Color.Black)
    )

}

