package com.example.campaingsandfriends.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Personaje

@Composable
fun CardPersonaje(
    modifier: Modifier,
    personaje: Personaje,
    onClick: (Long) -> Unit
){
    Card(
        modifier = modifier.height(100.dp).fillMaxWidth(),
        onClick = { onClick(personaje.id) }
    ){
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(com.example.campaingsandfriends.R.drawable.image_mokup),
                contentDescription = "Imagen personaje",
            )
            Column(
                modifier = Modifier.fillMaxSize().weight(1f).padding(10.dp)
            ) {
                Text(
                    text = personaje.nombre,
                    fontSize = 20.sp
                )
                Text(
                    text = "nivel: ${personaje.nivel.toString()} | raza: ${personaje.raza}"
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