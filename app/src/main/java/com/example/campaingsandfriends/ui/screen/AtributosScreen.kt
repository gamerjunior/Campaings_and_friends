package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Personaje

@Composable
fun AtributosScreen(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    val atributos = personaje.atributosFinales
    val listaAtributos = listOf(
        "Fuerza" to atributos.fuerza,
        "Destreza" to atributos.destreza,
        "Constitución" to atributos.constitucion,
        "Inteligencia" to atributos.inteligencia,
        "Sabiduría" to atributos.sabiduria,
        "Carisma" to atributos.carisma
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listaAtributos.size) { index ->
            val (nombre, valor) = listaAtributos[index]
            val mod = atributos.obtenerModificador(valor)
            val modText = if (mod >= 0) "+$mod" else "$mod"

            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = nombre, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$valor", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                    Text(text = "Mod: $modText", fontSize = 14.sp)
                }
            }
        }
    }
}
