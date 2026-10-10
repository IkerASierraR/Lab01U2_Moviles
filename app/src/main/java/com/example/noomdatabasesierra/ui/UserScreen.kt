package com.example.noomdatabasesierra.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noomdatabasesierra.viewmodel.MainViewModel

@Composable
fun UserScreen(viewModel: MainViewModel, modifier: Modifier = Modifier){

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val users by viewModel.users.collectAsState(initial = emptyList())

    Column(modifier = modifier.padding(16.dp)) {

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = {apellido = it},
            label = {Text("Apellido")},
            modifier = Modifier.fillMaxWidth()
        )

        if (error != null){
            Text(text = error!!, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                error = viewModel.guardar(nombre, apellido)
                if(error == null){
                    nombre = ""
                    apellido = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        LazyColumn {
            items(users){user ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)){
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${user.nombre} - ${user.apellido}",
                            modifier = Modifier.weight(1f)
                        )
                        TextButton(onClick = { viewModel.eliminar(user)}){
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}