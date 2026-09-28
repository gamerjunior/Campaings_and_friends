package com.example.campaingsandfriends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.ui.screen.MainScreen
import com.example.campaingsandfriends.ui.theme.CampaingsAndFriendsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampaingsAndFriendsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(8.dp),
                        campanias = listOf(
                            Campania(
                                nombre = "Golpe de los dragones de oror",
                                master = "pablo",
                                Njugadores = 3,
                                jugadores = arrayListOf("saul","carlos","josevi"),
                                anotaciones = arrayListOf("nada","nada dos"),
                                Nsesiones = 3
                            ),
                            Campania(
                                    nombre = "Nunca brilla el sol",
                                    master = "saul",
                                    Njugadores = 5,
                                    jugadores = arrayListOf("jose","marcos","edu","mi padre","lola lolita"),
                                    anotaciones = arrayListOf("nada","nada dos"),
                                    Nsesiones = 8
                            ),
                            Campania(
                                nombre = "buscarle novia al carlos",
                                master = "jose",
                                Njugadores = 4,
                                jugadores = arrayListOf("josevi","adrian","paco","abuelo carlos"),
                                anotaciones = arrayListOf("nada","nada dos"),
                                Nsesiones = 5
                            ),
                            Campania(
                                nombre = "la herencia de lord sebastian",
                                master = "saul",
                                Njugadores = 1,
                                jugadores = arrayListOf("zaida"),
                                anotaciones = arrayListOf("nada","nada dos"),
                                Nsesiones = 3
                            ),

                        )
                    )
                }
            }
        }
    }
}

