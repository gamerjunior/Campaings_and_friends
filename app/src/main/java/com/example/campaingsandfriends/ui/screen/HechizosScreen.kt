package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.campaingsandfriends.ui.theme.Primario

@Composable
fun HechizosScreen(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (!personaje.esLanzadorDeConjuros) {
            Text(
                text = "Este personaje no es un lanzador de conjuros.",
                fontSize = 16.sp
            )
        } else {
            Text(
                text = "Espacios de Conjuro (Slots):",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            val slots = personaje.slotsMaximos
            slots.forEachIndexed { nivelConjuro, maxSlots ->
                if (nivelConjuro > 0 && maxSlots > 0) {
                    val actuales = personaje.obtenerSlotsActualesPorNivel(nivelConjuro)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Primario
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Nivel $nivelConjuro",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$actuales / $maxSlots disponibles",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
