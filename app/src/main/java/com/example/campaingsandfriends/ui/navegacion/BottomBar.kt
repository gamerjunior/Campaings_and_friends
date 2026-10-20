package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.campaingsandfriends.R

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
            icon = { Icon(painter = painterResource(R.drawable.home_24px), contentDescription = "Home") },
            label = { Text("campañas")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.DADOS,
            onClick = { navController.navigate(Rutas.DADOS) },
            icon = { Icon(painter = painterResource(R.drawable.dados), contentDescription = "Dados") },
            label = { Text("dados")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL) },
            icon = { Icon(painter = painterResource(R.drawable.person_24px), contentDescription = "Perfil") },
            label = { Text("perfil")}
        )


    }
}
    

