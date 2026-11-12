package com.example.campaingsandfriends.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campaingsandfriends.model.Atributos
import com.example.campaingsandfriends.model.CatalogoRazas
import com.example.campaingsandfriends.model.CatalogoSubclases
import com.example.campaingsandfriends.model.CatalogoSubrazas
import com.example.campaingsandfriends.model.ClaseDnd
import com.example.campaingsandfriends.model.Personaje
import com.example.campaingsandfriends.model.PuntosDeVida
import com.example.campaingsandfriends.model.Subclase
import com.example.campaingsandfriends.model.SubrazaDnd
import com.example.campaingsandfriends.model.TipoCompetencia
import com.example.campaingsandfriends.validacion.PersonajeFormValidator

// Función auxiliar para calcular el costo de los atributos
private fun calcularCostoAtributo(valor: Int): Int {
    return when {
        valor <= 8 -> 0
        valor == 9 -> 1
        valor == 10 -> 2
        valor == 11 -> 3
        valor == 12 -> 4
        valor == 13 -> 5
        valor == 14 -> 7
        valor == 15 -> 9
        else -> 9
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonajeFormScreen(
    modifier: Modifier = Modifier,
    personajeInicial: Personaje? = null,
    onSaveClick: (Personaje) -> Unit,
    onCancelarClick: () -> Unit
) {
    var nombre by remember { mutableStateOf(personajeInicial?.nombre ?: "") }
    var razaSeleccionada by remember { mutableStateOf(personajeInicial?.raza ?: CatalogoRazas.VACIO) }
    var subrazaSeleccionada by remember { mutableStateOf<SubrazaDnd?>(personajeInicial?.subraza) }
    var claseSeleccionada by remember { mutableStateOf(personajeInicial?.clase ?: ClaseDnd.VACIO) }
    var subclaseSeleccionada by remember { mutableStateOf<Subclase?>(personajeInicial?.subclase) }
    var nivel by remember { mutableStateOf(personajeInicial?.nivel?.toString() ?: "1") }

    // Atributos Base (Point Buy: inician en 8, máximo 15, pool de 27 puntos)
    var fueBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.fuerza ?: 8) }
    var desBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.destreza ?: 8) }
    var conBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.constitucion ?: 8) }
    var intBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.inteligencia ?: 8) }
    var sabBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.sabiduria ?: 8) }
    var carBase by remember { mutableIntStateOf(personajeInicial?.atributosBase?.carisma ?: 8) }

    // Cálculo de puntos usados y disponibles en el pool de 27
    val puntosUsados =
            calcularCostoAtributo(fueBase) +
            calcularCostoAtributo(desBase) +
            calcularCostoAtributo(conBase) +
            calcularCostoAtributo(intBase) +
            calcularCostoAtributo(sabBase) +
            calcularCostoAtributo(carBase)
    val puntosDisponibles = 27 - puntosUsados

    // Competencias en Habilidades elegidas
    var competenciasSeleccionadas by remember {
        mutableStateOf(
            personajeInicial?.competenciasHabilidades
                ?.filterValues { it != TipoCompetencia.NINGUNA }
                ?.keys
                ?.toSet() ?: emptySet()
        )
    }

    // Control del menú desplegable de Raza / Clase
    var expandedRaza by remember { mutableStateOf(false) }
    var expandedSubraza by remember { mutableStateOf(false) }
    var expandedClase by remember { mutableStateOf(false) }
    var expandedSubclase by remember { mutableStateOf(false) }

    // Estado de errores de validación
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorNivel by remember { mutableStateOf<String?>(null) }
    var errorHabilidades by remember { mutableStateOf<String?>(null) }
    var errorClase by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()
    val subrazasDisponibles = CatalogoSubrazas.obtenerSubrazasPara(razaSeleccionada.nombreId)
    val subclasesDisponibles = CatalogoSubclases.obtenerSubclasesPara(claseSeleccionada)
    val nivelActual = nivel.toIntOrNull() ?: 1
    val puedeElegirSubclase = nivelActual >= claseSeleccionada.nivelDesbloqueoSubclase

    // Modificadores raciales dinámicos (Raza + Subraza)
    val bonusFuerza = razaSeleccionada.modificadoresAtributos.fuerza + (subrazaSeleccionada?.modificadoresAtributos?.fuerza ?: 0)
    val bonusDestreza = razaSeleccionada.modificadoresAtributos.destreza + (subrazaSeleccionada?.modificadoresAtributos?.destreza ?: 0)
    val bonusConstitucion = razaSeleccionada.modificadoresAtributos.constitucion + (subrazaSeleccionada?.modificadoresAtributos?.constitucion ?: 0)
    val bonusInteligencia = razaSeleccionada.modificadoresAtributos.inteligencia + (subrazaSeleccionada?.modificadoresAtributos?.inteligencia ?: 0)
    val bonusSabiduria = razaSeleccionada.modificadoresAtributos.sabiduria + (subrazaSeleccionada?.modificadoresAtributos?.sabiduria ?: 0)
    val bonusCarisma = razaSeleccionada.modificadoresAtributos.carisma + (subrazaSeleccionada?.modificadoresAtributos?.carisma ?: 0)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = if (personajeInicial == null) "Crear Nuevo Personaje" else "Editar Personaje",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        // Nombre
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Personaje") },
            isError = errorNombre != null,
            supportingText = { errorNombre?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }
        )

        // Selección de Raza
        ExposedDropdownMenuBox(
            expanded = expandedRaza,
            onExpandedChange = { expandedRaza = !expandedRaza }
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                readOnly = true,
                value = razaSeleccionada.nombreVisible,
                onValueChange = {},
                label = { Text("Raza") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRaza) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedRaza,
                onDismissRequest = { expandedRaza = false }
            ) {
                CatalogoRazas.todasRazas.forEach { raza ->
                    DropdownMenuItem(
                        text = { Text(raza.nombreVisible) },
                        onClick = {
                            razaSeleccionada = raza
                            subrazaSeleccionada = null
                            expandedRaza = false
                        }
                    )
                }
            }
        }

        // Selección de Subraza (si aplica)
        if (subrazasDisponibles.isNotEmpty()) {
            ExposedDropdownMenuBox(
                expanded = expandedSubraza,
                onExpandedChange = { expandedSubraza = !expandedSubraza }
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                    readOnly = true,
                    value = subrazaSeleccionada?.nombreVisible ?: "Ninguna",
                    onValueChange = {},
                    label = { Text("Subraza") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSubraza) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedSubraza,
                    onDismissRequest = { expandedSubraza = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Ninguna") },
                        onClick = {
                            subrazaSeleccionada = null
                            expandedSubraza = false
                        }
                    )
                    subrazasDisponibles.forEach { subraza ->
                        DropdownMenuItem(
                            text = { Text(subraza.nombreVisible) },
                            onClick = {
                                subrazaSeleccionada = subraza
                                expandedSubraza = false
                            }
                        )
                    }
                }
            }
        }

        // Selección de Clase
        ExposedDropdownMenuBox(
            expanded = expandedClase,
            onExpandedChange = { expandedClase = !expandedClase }
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                readOnly = true,
                value = claseSeleccionada.nombreVisible,
                onValueChange = {},
                label = { Text("Clase") },
                isError = errorClase != null,
                supportingText = { errorClase?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedClase) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedClase,
                onDismissRequest = { expandedClase = false }
            ) {
                ClaseDnd.entries.forEach { clase ->
                    DropdownMenuItem(
                        text = { Text(clase.nombreVisible) },
                        onClick = {
                            claseSeleccionada = clase
                            subclaseSeleccionada = null
                            expandedClase = false
                            // Filtrar competencias para conservar solo las válidas para la nueva clase
                            competenciasSeleccionadas = competenciasSeleccionadas.filter { it in clase.habilidadesDisponibles }.toSet()
                        }
                    )
                }
            }
        }

        // Selección de Subclase (si aplica y el nivel del personaje es suficiente)
        if (subclasesDisponibles.isNotEmpty() && puedeElegirSubclase) {
            ExposedDropdownMenuBox(
                expanded = expandedSubclase,
                onExpandedChange = { expandedSubclase = !expandedSubclase }
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                    readOnly = true,
                    value = subclaseSeleccionada?.nombreVisible ?: "Ninguna",
                    onValueChange = {},
                    label = { Text("Subclase (Nivel ${claseSeleccionada.nivelDesbloqueoSubclase}+)") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSubclase) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedSubclase,
                    onDismissRequest = { expandedSubclase = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Ninguna") },
                        onClick = {
                            subclaseSeleccionada = null
                            expandedSubclase = false
                        }
                    )
                    subclasesDisponibles.forEach { subclase ->
                        DropdownMenuItem(
                            text = { Text(subclase.nombreVisible) },
                            onClick = {
                                subclaseSeleccionada = subclase
                                expandedSubclase = false
                            }
                        )
                    }
                }
            }
        }

        // Nivel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = nivel,
                onValueChange = { nivel = it },
                label = { Text("Nivel") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorNivel != null,
                supportingText = { errorNivel?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }
            )
        }

        // Encabezado del Sistema de Puntos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Atributos Base", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (puntosDisponibles >= 0) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = "Puntos: $puntosDisponibles / 27",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }

        // Tarjetas de control de Atributos Base (Point Buy)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AtributoControlCard(
                nombre = "Fuerza",
                valorBase = fueBase,
                bonusRaza = bonusFuerza,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { fueBase = it },
                modifier = Modifier.weight(1f)
            )
            AtributoControlCard(
                nombre = "Destreza",
                valorBase = desBase,
                bonusRaza = bonusDestreza,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { desBase = it },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AtributoControlCard(
                nombre = "Constitución",
                valorBase = conBase,
                bonusRaza = bonusConstitucion,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { conBase = it },
                modifier = Modifier.weight(1f)
            )
            AtributoControlCard(
                nombre = "Inteligencia",
                valorBase = intBase,
                bonusRaza = bonusInteligencia,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { intBase = it },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AtributoControlCard(
                nombre = "Sabiduría",
                valorBase = sabBase,
                bonusRaza = bonusSabiduria,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { sabBase = it },
                modifier = Modifier.weight(1f)
            )
            AtributoControlCard(
                nombre = "Carisma",
                valorBase = carBase,
                bonusRaza = bonusCarisma,
                puntosDisponibles = puntosDisponibles,
                onValueChange = { carBase = it },
                modifier = Modifier.weight(1f)
            )
        }

        // Selección de Habilidades de Clase
        if (claseSeleccionada.habilidadesDisponibles.isNotEmpty()) {
            Text(
                text = "Habilidades de Clase (Elige ${claseSeleccionada.cantidadHabilidadesAElegir})",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Seleccionadas: ${competenciasSeleccionadas.size} / ${claseSeleccionada.cantidadHabilidadesAElegir}",
                fontSize = 14.sp,
                color = if (competenciasSeleccionadas.size == claseSeleccionada.cantidadHabilidadesAElegir) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            errorHabilidades?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            claseSeleccionada.habilidadesDisponibles.forEach { habilidad ->
                val isChecked = habilidad in competenciasSeleccionadas
                val canSelectMore = competenciasSeleccionadas.size < claseSeleccionada.cantidadHabilidadesAElegir

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            competenciasSeleccionadas = if (checked) {
                                if (canSelectMore) competenciasSeleccionadas + habilidad else competenciasSeleccionadas
                            } else {
                                competenciasSeleccionadas - habilidad
                            }
                        },
                        enabled = isChecked || canSelectMore
                    )
                    Text(
                        text = "${habilidad.nombreVisible} (${habilidad.atributoAsociado})",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Botones de acción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
        ) {
            TextButton(onClick = { onCancelarClick() }) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    errorNombre = PersonajeFormValidator.validarNombre(nombre)
                    errorNivel = PersonajeFormValidator.validarNivel(nivel)
                    errorHabilidades = PersonajeFormValidator.validarHabilidades(
                        seleccionadas = competenciasSeleccionadas.size,
                        requeridas = claseSeleccionada.cantidadHabilidadesAElegir
                    )
                    errorClase = PersonajeFormValidator.validarClase(claseSeleccionada)

                    if (errorNombre == null && errorNivel == null && errorHabilidades == null && errorClase == null && puntosDisponibles >= 0) {
                        val competenciasMap = competenciasSeleccionadas.associateWith { TipoCompetencia.COMPETENTE }

                        val nuevoPersonaje = Personaje(
                            id = personajeInicial?.id ?: System.currentTimeMillis(),
                            nombre = nombre,
                            raza = razaSeleccionada,
                            subraza = subrazaSeleccionada,
                            clase = claseSeleccionada,
                            subclase = subclaseSeleccionada,
                            nivel = nivel.toIntOrNull() ?: 1,
                            atributosBase = Atributos(
                                fuerza = fueBase,
                                destreza = desBase,
                                constitucion = conBase,
                                inteligencia = intBase,
                                sabiduria = sabBase,
                                carisma = carBase
                            ),
                            vida = PuntosDeVida(
                                maximos = claseSeleccionada.dadoDeGolpe,
                                actuales = claseSeleccionada.dadoDeGolpe,
                                temporales = 0
                            ),
                            competenciasHabilidades = competenciasMap
                        )
                        onSaveClick(nuevoPersonaje)
                    }
                }
            ) {
                Text("Guardar")
            }
        }
    }
}

@Composable
private fun AtributoControlCard(
    nombre: String,
    valorBase: Int,
    bonusRaza: Int,
    puntosDisponibles: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val total = valorBase + bonusRaza
    val mod = (total - 10) / 2
    val modText = if (mod >= 0) "+$mod" else "-$mod"
    val bonusText = if (bonusRaza >= 0) "+$bonusRaza" else "-$bonusRaza"

    val costoIncremento = if (valorBase >= 13) 2 else 1
    val puedeIncrementar = valorBase < 15 && puntosDisponibles >= costoIncremento
    val puedeDecrementar = valorBase > 8

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { if (puedeDecrementar) onValueChange(valorBase - 1) },
                    enabled = puedeDecrementar
                ) {
                    Text("-", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "$valorBase",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                IconButton(
                    onClick = { if (puedeIncrementar) onValueChange(valorBase + 1) },
                    enabled = puedeIncrementar
                ) {
                    Text("+", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }

            Text(
                text = "Raza: $bonusText | Total: $total ($modText)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
