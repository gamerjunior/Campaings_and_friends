package com.example.campaingsandfriends.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.R
import com.example.campaingsandfriends.model.Campania

@Composable
fun CardCampania(
    modifier: Modifier,
    campania: Campania,
){
    Card(
        modifier = modifier
    ) {
        Column(
            Modifier.fillMaxSize().padding(10.dp).height(200.dp).align(Alignment.CenterHorizontally), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally).size(64.dp).weight(0.3f),
                painter = painterResource(R.drawable.image_mokup),
                contentDescription = "Imagen campaña"
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).weight(0.3f),
                text = campania.nombre,

            )


            Row(Modifier.align(Alignment.CenterHorizontally).weight(0.4f)) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.drawable.group),
                    contentDescription = "Imagen jugadores",
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = ": ${campania.Njugadores}"
                )

                Spacer(Modifier.width(50.dp))

                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.drawable.image_mokup),
                    contentDescription = "Imagen sesiones",
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = ": ${campania.Nsesiones}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCampaniaPreview(){
    CardCampania(modifier = Modifier,
        campania = Campania(
            nombre = "camapña de prueba",
            master = "saúl",
            Njugadores = 2,
            jugadores = arrayListOf("zaida","leo") ,
            anotaciones = arrayListOf("primera sesion:nada","segunda sesion:nada"),
            Nsesiones = 3
        ))
}