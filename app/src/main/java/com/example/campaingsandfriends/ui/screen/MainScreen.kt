package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.ui.components.CardCampania


@Composable
fun MainScreen(
    modifier: Modifier,
    campanias: List<Campania>
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
     Text(text = "Campaings and Friends", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(8.dp))

        CampaniaList(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                ,
            campanias = campanias
        )

    }
}
@Composable
fun CampaniaList(
    modifier: Modifier,
    campanias: List<Campania>
){
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(20){
            CardCampania(
                campania = campanias.random(),
                modifier = Modifier.fillMaxWidth().size(370.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CampaniaListPreview(){
    CampaniaList(
        modifier = Modifier,
        campanias = listOf(
            Campania(
                nombre = "golpe de los dragones de oro",
                master = "Saúl",
                Njugadores = 5,
                jugadores = arrayListOf("zaida","leo","leo") ,
                anotaciones = arrayListOf("sesion 1","sesion 2","sesion 3"),
                Nsesiones = 2
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(
        Modifier,
        listOf(Campania(
            nombre = "golpe de los dragones de oro",
            master = "Saúl",
            Njugadores = 5,
            jugadores = arrayListOf("zaida","leo","leo") ,
            anotaciones = arrayListOf("sesion 1","sesion 2","sesion 3"),
            Nsesiones = 2
        ))
    )
}
