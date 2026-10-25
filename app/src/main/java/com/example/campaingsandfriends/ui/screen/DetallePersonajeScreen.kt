package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Personaje

@Composable
fun DetallePersonajeScreen(
    modifier: Modifier,
    personaje: Personaje

) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Row(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier.size(128.dp),
                painter = painterResource(com.example.campaingsandfriends.R.drawable.image_mokup),
                contentDescription = "Imagen personaje"
            )
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${personaje.nombre} | raza: ${personaje.raza}",
                    fontSize = 30.sp
                )
                Text(
                    text = "clase: ${personaje.clase} de nivel ${personaje.nivel}",
                    fontSize = 30.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Vida: ${personaje.vida}",
                fontSize = 30.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Text(text = "Armura: ",
                fontSize = 30.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            estadisticas(modifier.weight(0.3f))
            estadisticas(modifier.weight(0.3f))
            estadisticas(modifier.weight(0.3f))
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            estadisticas(modifier.weight(0.3f))
            estadisticas(modifier.weight(0.3f))
            estadisticas(modifier.weight(0.3f))
        }

    }
}


@Composable
fun estadisticas(
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .size(125.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Column(

        ){
            Text("N", fontSize = 20.sp)
            Text("M", fontSize = 20.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetallePersonajeScreenPreview(
){
    DetallePersonajeScreen(
        modifier = Modifier,
        personaje = Personaje(
            nombre = "saúl",
            raza = "humano",
            clase = "guerrero",
            nivel = 10,
            vida = 10,
            id = 1
        )
    )
}


