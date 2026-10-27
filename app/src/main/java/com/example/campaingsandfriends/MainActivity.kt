package com.example.campaingsandfriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.campaingsandfriends.ui.navegacion.BottomBar
import com.example.campaingsandfriends.ui.navegacion.TopBar
import com.example.campaingsandfriends.ui.theme.CampaingsAndFriendsTheme
import androidx.navigation.compose.rememberNavController
import com.example.campaingsandfriends.ui.navegacion.AppNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            var nombrePersonajeSeleccionado by remember { mutableStateOf("") }

            CampaingsAndFriendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopBar(
                                navController = navController,
                                nombrePersonaje = nombrePersonajeSeleccionado
                            )
                        },
                        bottomBar = {BottomBar(navController = navController)}
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        onPersonajeCargado = {nombre ->
                            nombrePersonajeSeleccionado = nombre
                        }
                    )

                }
            }
        }
    }
}

