package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.remote.creation.Rc
import androidx.compose.remote.creation.dsl.fillParentMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.ui.components.CardCampania


@Composable
fun HomeScreen(
    modifier: Modifier,
    campanias: List<Campania>,
    onCampaniaListClick: (Long) -> Unit,
    onNuevaCampaniaClick: () -> Unit
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
            campanias = campanias,
            onCampaniaClick = {onCampaniaListClick(it)}
        )

        nuevaCampania(
            modifier = Modifier
                .padding(8.dp)
                ,
            onNuevaCampaniaClick = {onNuevaCampaniaClick()}
        )

    }
}
@Composable
fun CampaniaList(
    modifier: Modifier,
    campanias: List<Campania>,
    onCampaniaClick: (Long) -> Unit
){
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(campanias.size){ campania ->
            CardCampania(
                campania = campanias[campania],
                modifier = Modifier.fillMaxWidth().size(370.dp),
                onClick = {onCampaniaClick(it)}
            )
        }
    }
}

@Composable
fun nuevaCampania(
    modifier: Modifier,
    onNuevaCampaniaClick: () -> Unit
){
    Card(
        modifier = modifier.fillMaxWidth().height(64.dp),
        onClick = {onNuevaCampaniaClick()}

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "añadir nueva campaña")
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
                jugadores = arrayListOf("zaida", "leo", "leo"),
                anotaciones = arrayListOf("sesion 1", "sesion 2", "sesion 3"),
                Nsesiones = 2,
                id = 1
            )
        ),
        onCampaniaClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    HomeScreen(
        Modifier,
        listOf(Campania(
            nombre = "golpe de los dragones de oro",
            master = "Saúl",
            Njugadores = 5,
            jugadores = arrayListOf("zaida","leo","leo") ,
            anotaciones = arrayListOf("sesion 1","sesion 2","sesion 3"),
            Nsesiones = 2,
            id = 2
        )),
        onCampaniaListClick = {},
        onNuevaCampaniaClick = {}
    )
}
