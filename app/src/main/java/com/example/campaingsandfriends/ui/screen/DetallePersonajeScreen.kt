package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Personaje

@Composable
fun DetallePersonajeScreen(
    modifier: Modifier,
    personaje: Personaje,
    onPersonajeCargado: (String) -> Unit = {}
) {
    LaunchedEffect(personaje.nombre) {
        onPersonajeCargado(personaje.nombre)
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Atributos", "Habilidades", "Rasgos", "Conjuros")

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier.size(128.dp),
            ) {
                Text(
                    text = "Iniciativa",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
                Text(
                    text = "\n\n\n${personaje.atributosFinales.obtenerModificador(personaje.atributosFinales.destreza)}",
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Image(
                modifier = Modifier.size(128.dp),
                painter = painterResource(com.example.campaingsandfriends.R.drawable.image_mokup),
                contentDescription = "Imagen personaje"
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Vida: ${personaje.vida.actuales}/${personaje.vida.maximos}",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Text(
                text = "Armadura: ",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex
        ) {
            tabs.forEachIndexed { index, titulo ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = titulo,
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> AtributosScreen(personaje = personaje, modifier = Modifier.weight(1f))
            1 -> HabilidadesScreen(personaje = personaje, modifier = Modifier.weight(1f))
            2 -> RasgosScreen(personaje = personaje, modifier = Modifier.weight(1f))
            3 -> HechizosScreen(personaje = personaje, modifier = Modifier.weight(1f))
        }
    }
}
