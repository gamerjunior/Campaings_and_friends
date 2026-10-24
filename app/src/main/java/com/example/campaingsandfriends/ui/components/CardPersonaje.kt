package com.example.campaingsandfriends.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Personaje

@Composable
fun CardPersonaje(
    modifier: Modifier,
    personaje: Personaje,
    onClick: (Long) -> Unit
){
    Card(
        modifier = modifier.height(100.dp).width(200.dp).fillMaxSize(),
        onClick = { onClick(personaje.id) }
    ){
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(com.example.campaingsandfriends.R.drawable.image_mokup),
                contentDescription = "Imagen personaje",
            )
            Column(

            ) {
                Text(
                    text = personaje.nombre
                )
                Text(
                    text = " nivel: ${personaje.nivel.toString()} | raza: ${personaje.raza}"
                )
                Text(
                    text = "clase: ${personaje.clase}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPersonajePreview(){
    CardPersonaje(modifier = Modifier,
        personaje = Personaje(
            nombre = "saúl",
            raza = "humano",
            clase = "guerrero",
            nivel = 10,
            vida = 10,
            id = 1),
        onClick = {})
}