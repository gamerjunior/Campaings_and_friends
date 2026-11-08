package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.ui.theme.Primario

@Composable
fun RasgosScreen(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    val rasgos = personaje.todosLosRasgos

    if (rasgos.isEmpty()) {
        Text(
            text = "Sin rasgos disponibles",
            modifier = modifier.padding(16.dp),
            fontSize = 16.sp
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(rasgos.size) { index ->
                val rasgo = rasgos[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Primario
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "${rasgo.nombre} (Nivel ${rasgo.nivelRequerido})",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = rasgo.descripcion,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
