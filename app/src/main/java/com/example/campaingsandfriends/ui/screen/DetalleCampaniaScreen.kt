package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.campaingsandfriends.R
import com.example.campaingsandfriends.model.Campania

@Composable
fun DetalleCampaniaScreen(
    modifier: Modifier,
    campania: Campania
){
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Top

    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally).size(256.dp),
            painter = painterResource(R.drawable.image_mokup),
            contentDescription = "Imagen campaña"
        )

        DatoCampania(campania.nombre)
        DatoCampania(campania.master)
        DatoCampania(campania.Njugadores.toString())
        DatoCampania(campania.Nsesiones.toString())

        Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).align(Alignment.CenterHorizontally), text = "Anotaciones")

        anotacionesList(
            modifier = Modifier,
            anotaciones = campania.anotaciones
        )
    }
}

@Composable
fun DatoCampania(texto: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(texto, fontSize = 16.sp)
    }
}


@Composable
fun anotacion(texto: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(texto, fontSize = 16.sp)
    }
}

@Composable
fun anotacionesList(
    modifier: Modifier,
    anotaciones: List<String>){

    LazyColumn(
        modifier = modifier
    ){
        items(anotaciones.size){
            anotacion(anotaciones[it])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleCampaniaScreenPreview(){
    DetalleCampaniaScreen(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        campania = Campania(
            nombre = "camapña de prueba",
            master = "saúl",
            Njugadores = 2,
            jugadores = arrayListOf("zaida","leo") ,
            anotaciones = arrayListOf("primera sesion:nada","segunda sesion:nada","tercera sesion:nada","cuarta sesion:nada"),
            Nsesiones = 3,
            id = 1
        ))
}