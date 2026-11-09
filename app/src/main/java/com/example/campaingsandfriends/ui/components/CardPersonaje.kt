package com.example.campaingsandfriends.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.R
import com.example.campaingsandfriends.model.CatalogoRazas
import com.example.campaingsandfriends.model.ClaseDnd
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.model.PuntosDeVida

@Composable
fun CardPersonaje(
    modifier: Modifier,
    personaje: Personaje,
    onClick: (Long) -> Unit,
){
    Card(
        modifier = modifier.height(100.dp).fillMaxWidth(),
        onClick = { onClick(personaje.id) }
    ){
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.size(80.dp).align(Alignment.CenterVertically),
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
                    text = "nivel: ${personaje.nivel.toString()} | raza: ${personaje.raza.nombreVisible}"
                )
                Text(
                    text = "clase: ${personaje.clase.nombreVisible} | subclase: ${personaje.subclase?.nombreVisible ?: "Ninguna"}"
                )
            }

        }
    }
}

//completar para la edicion y eliminacion de personajes
@Composable
fun MenuEdicion(
    onClick: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(painter = painterResource(id = R.drawable.edit), contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Editar") },
                onClick = { onClick }
            )
            DropdownMenuItem(
                text = { Text("Borrar") },
                onClick = { /* Do something... */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPersonajePreview(){
    CardPersonaje(modifier = Modifier,
        Personaje(
            nombre = "saúl",
            raza = CatalogoRazas.HUMANO,
            clase = ClaseDnd.GUERRERO,
            nivel = 10,
            vida = PuntosDeVida(
                maximos = 10,
                actuales = 10,
                temporales = 0
            ),
            id = 1
        ),
        onClick = {})
}