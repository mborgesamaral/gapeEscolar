package com.example.gapeescolar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gapeescolar.ui.theme.ÁgapeEscolarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ÁgapeEscolarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaLogin()
                }
            }
        }
    }
}

@OptIn(MaterialTheme::class)
@Composable
fun TelaLogin() {
    // Variável que guarda o texto do CPF digitado em tempo real
    var cpfInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sistema Ágape Escolar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo de Texto para digitar o CPF
        OutlinedTextField(
            value = cpfInput,
            onValueChange = { input ->
                // Permite apenas números e limita ao tamanho padrão do CPF (11 dígitos)
                if (input.all { it.isDigit() } && input.length <= 11) {
                    cpfInput = input
                }
            },
            label = { Text("Digite o CPF do Funcionário") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão para acionar a verificação
        Button(
            onClick = {
                if (cpfInput.length == 11) {
                    // AQUI vai entrar a nossa busca ao Firebase na próxima etapa
                    Toast.makeText(context, "Buscando CPF: $cpfInput...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Por favor, digite um CPF válido com 11 dígitos", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar no Sistema")
        }
    }
}