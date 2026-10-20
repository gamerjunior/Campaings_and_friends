package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import com.example.campaingsandfriends.model.Campania

@Composable
fun DetalleCampaniaScreen(
    modifier: Modifier,
    campania: Campania
){
    Column(
        modifier = modifier,

    ) {
        Text(text = "Titulo de campaña: " + campania.nombre)
        Text(text = "Master: " + campania.master)
        Text(text = "Numero de jugadores: "+campania.Njugadores.toString())
        Text(text = "Numero de sesiones jugadas: " + campania.Nsesiones.toString())
    }
}