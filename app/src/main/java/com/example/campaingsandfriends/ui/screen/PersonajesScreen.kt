package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.CatalogoRazas
import com.example.campaingsandfriends.model.ClaseDnd
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.model.PuntosDeVida
import com.example.campaingsandfriends.ui.components.CardPersonaje

@Composable
fun PersonajesScreen(
    modifier: Modifier = Modifier,
    personajes: List<Personaje>,
    onPersonajeClick: (Long) -> Unit,
    onNuevoPersonajeClick: () -> Unit = {}
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        PersonajeList(
            modifier = Modifier.weight(1f),
            personajes = personajes,
            onPersonajeClick = { onPersonajeClick(it) }
        )

        NuevoPersonajeCard(
            modifier = Modifier.padding(8.dp),
            onNuevoPersonajeClick = onNuevoPersonajeClick
        )
    }
}

@Composable
fun PersonajeList(
    modifier: Modifier,
    personajes: List<Personaje>,
    onPersonajeClick: (Long) -> Unit
){
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(personajes.size){ personaje ->
            CardPersonaje(
                personaje = personajes[personaje],
                onClick = { onPersonajeClick(it) },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun NuevoPersonajeCard(
    modifier: Modifier = Modifier,
    onNuevoPersonajeClick: () -> Unit
){
    Card(
        modifier = modifier.fillMaxWidth().height(64.dp),
        onClick = { onNuevoPersonajeClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Añadir nuevo personaje")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonajesScreenPreview(){
    PersonajesScreen(
        modifier = Modifier,
        personajes = listOf(
            Personaje(
                nombre = "saúl",
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
        ),
        onPersonajeClick = {},
        onNuevoPersonajeClick = {}
    )
}
