package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

@Composable
fun BottomBar (
    navController: NavHostController,
){
    NavigationBar(){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { navController.navigate(Rutas.HOME) },
            icon = TODO(),
            label = { Text("campañas")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.DADOS,
            onClick = { navController.navigate(Rutas.DADOS) },
            icon = TODO(),
            label = { Text("dados")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL) },
            icon = TODO(),
            label = { Text("perfil")}
        )


    }
}
    

