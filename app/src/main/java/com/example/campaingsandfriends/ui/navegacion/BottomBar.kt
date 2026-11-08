package com.example.campaingsandfriends.ui.navegacion

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.campaingsandfriends.R
import com.example.campaingsandfriends.ui.theme.Primario

@Composable
fun BottomBar (
    navController: NavHostController,
){
    NavigationBar(
        containerColor = Primario
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { navController.navigate(Rutas.HOME) },
            icon = { Icon(painter = painterResource(R.drawable.home_24px), contentDescription = "Home", modifier = Modifier.size(24.dp)) },
            label = { Text("campañas")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.DADOS,
            onClick = { navController.navigate(Rutas.DADOS) },
            icon = { Icon(painter = painterResource(R.drawable.dados), contentDescription = "Dados", modifier = Modifier.size(24.dp)) },
            label = { Text("dados")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERSONAJES,
            onClick = { navController.navigate(Rutas.PERSONAJES) },
            icon = { Icon(painter = painterResource(R.drawable.icono_dnd), contentDescription = "Personajes", modifier = Modifier.size(24.dp)) },
            label = { Text("personajes")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL) },
            icon = { Icon(painter = painterResource(R.drawable.person_24px), contentDescription = "Perfil", modifier = Modifier.size(24.dp)) },
            label = { Text("perfil")}
        )
    }
}
    
@Preview
@Composable
fun BottomBarPreview(){
    BottomBar(
        navController = NavHostController(LocalContext.current)
    )
}


