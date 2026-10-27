package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.ui.components.CardPersonaje


@Composable
fun PersonajesScreen(
    modifier: Modifier,
    personajes: List<Personaje>,
    onPersonajeClick: (Long) -> Unit
){
    PersonajeList(
        modifier = modifier,
        personajes = personajes,
        onPersonajeClick = {onPersonajeClick(it)}
    )
}

@Composable
fun PersonajeList(
    modifier: Modifier,
    personajes: List<Personaje>,
    onPersonajeClick: (Long) -> Unit
){
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp , vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(personajes.size){
            CardPersonaje(
                personaje = personajes[it],
                onClick = { onPersonajeClick(it) },
                modifier = Modifier
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PersonajeListPreview(){
    PersonajeList(
        modifier = Modifier,
        personajes = listOf(
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            )
        ),
        onPersonajeClick = {}
    )
}

@Preview
@Composable
fun PersonajesScreenPreview(){
    PersonajesScreen(
        modifier = Modifier,
        personajes = listOf(
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            ),
            Personaje(
                nombre = "saúl",
                raza = "humano",
                clase = "guerrero",
                nivel = 10,
                vida = 10,
                id = 1
            )

        ),
        onPersonajeClick = {}
    )
}

