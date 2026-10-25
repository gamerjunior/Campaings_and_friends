

package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun DadoScreen(
    modifier: Modifier
) {

    var resultado by remember { mutableStateOf("Tira un dado") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = resultado,
            fontSize = 50.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(200.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            listOf(4, 6, 8).forEach { caras ->
                Button(
                    modifier = Modifier.weight(0.3f).size(150.dp),
                    onClick = {
                        resultado = "1d$caras: ${Random.nextInt(1, caras + 1)}"
                    }) {
                    Text("1d$caras", fontSize = 30.sp)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            listOf(10, 12, 20).forEach { caras ->
                Button(
                    modifier = Modifier.weight(0.3f).size(150.dp),
                    onClick = {
                        resultado = "1d$caras: ${Random.nextInt(1, caras + 1)}"
                    }) {
                    Text("1d$caras", fontSize = 30.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DadoScreenPreview() {
    DadoScreen(
        modifier = Modifier
    )
}