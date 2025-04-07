 package com.example.appaula

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appaula.ui.theme.AppAulaTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppAula()
                }
            }
        }
    }
}

@Composable
fun AppAula() {
    var nome by remember{ mutableStateOf("") }
    var endereco by remember{ mutableStateOf("") }
    var bairro by remember{ mutableStateOf("") }
    var cep by remember{ mutableStateOf("") }
    var cidade by remember{ mutableStateOf("") }
    var estado by remember{ mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp))  {
        Row(modifier = Modifier.padding(30.dp),
            Arrangement.Center) {
            Text("Banco Firebase")
        }

        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = endereco,
                onValueChange = { endereco = it },
                label = { Text("Endereço") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = bairro,
                onValueChange = { bairro = it },
                label = { Text("Bairro") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = cep,
                onValueChange = { cep = it },
                label = { Text("CEP") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = cidade,
                onValueChange = { cidade = it },
                label = { Text("Cidade") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            TextField(
                value = estado,
                onValueChange = { estado = it },
                label = { Text("Estado") }
            )
        }
        Row(modifier = Modifier.padding(8.dp)){
            Button(onClick = {
                val db = Firebase.firestore

                val Clientes = hashMapOf(
                    "Nome" to nome,
                    "Endereco" to endereco,
                    "Bairro" to bairro,
                    "Cep" to cep,
                    "Cidade" to cidade,
                    "Estado" to estado
                )

                db.collection("Clientes ")
                    .add(Clientes)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }) {
                Text("Salvar")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppAula()
    }
}