package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.campaingsandfriends.model.Campania
import com.example.campaingsandfriends.validacion.CampaniaFormValidator

@Composable
fun campaniaFormScreen(
    modifier: Modifier,
    campaniaInicial: Campania?,
    onSaveClick: (Campania) -> Unit,
    onCancelarClick: () -> Unit
){
    var nombre by remember{
        mutableStateOf(campaniaInicial?.nombre ?: "")
    }

    var master by remember{
        mutableStateOf(campaniaInicial?.master ?: "")
    }

    var Njugadores by remember{
        mutableStateOf(campaniaInicial?.Njugadores ?: "")
    }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorMaster by remember { mutableStateOf<String?>(null) }
    var errorNJugadores by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") },
            isError = errorNombre != null,
            supportingText = { errorNombre?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }

        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = master,
            onValueChange = {master = it},
            label = { Text("master") },
            isError = errorMaster != null,
            supportingText = { errorMaster?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }

        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = Njugadores,
            onValueChange = {Njugadores = it},
            label = { Text("Numero de jugadores") },
            isError = errorNJugadores != null,
            supportingText = { errorNJugadores?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }

        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
        ) {

            TextButton (
                onClick = {
                    onCancelarClick()
                }
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {

                    errorNombre = CampaniaFormValidator.validarNombre(nombre)
                    errorMaster = CampaniaFormValidator.validarMaster(master)


                    //Faltan el resto de Validaciones

                    if (errorNombre == null && errorMaster== null) {
                        onSaveClick(
                            Campania(
                                id = campaniaInicial?.id ?: 0,
                                nombre = nombre,
                                master = master,
                                Njugadores = 0,
                                jugadores = ArrayList<String>(),
                                anotaciones = ArrayList<String>(),
                                Nsesiones = 0,
                            )
                        )
                    }
                }
            ) {
                Text("Guardar")
            }
        }
    }
}

