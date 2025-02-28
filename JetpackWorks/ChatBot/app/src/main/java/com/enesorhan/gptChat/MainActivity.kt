package com.enesorhan.gptChat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enesorhan.gptChat.ui.theme.AiNotesTheme
import com.enesorhan.gptChat.viewModel.GptViewModel
import com.enesorhan.gptChat.views.ChatPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiNotesTheme {
                ChatPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {

    val hostState = remember { SnackbarHostState() }
    val tf = remember { mutableStateOf("") }
    val alertDefault = remember { mutableStateOf(false) }
    val alertSpecial = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = hostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = Color.White,
                    contentColor = Color.Blue,
                    actionColor = Color.DarkGray,
                    dismissActionContentColor = Color.Red,
                    actionContentColor = Color.Blue,
                )
            }

        },

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "User Interaction", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.orange),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {



            /*Button(onClick = {
                scope.launch {
                hostState.showSnackbar(message = "İnternet Bağlantısı Yok!")
                }
            }) {
                Text(text = "Default Snackbar")
            }
            Button(onClick = {
                scope.launch {
                val sb= hostState.showSnackbar(message = "İnternet Baglantisi Yok!", actionLabel = "Tekrar Dene",
                    withDismissAction = true, duration = SnackbarDuration.Short)

                    if (sb==SnackbarResult.ActionPerformed){
                        hostState.showSnackbar(message = "Baglandi")
                    }
                    if (sb==SnackbarResult.Dismissed){
                        hostState.showSnackbar(message = "Baglanti Hatasi")
                    }
                }
            }) {
                Text(text = "Special Snackbar")
            }
            Button(onClick = {
                alertDefault.value=true
            }) {
                Text(text = "Default AlertDialog")
            }
            Button(onClick = {
                alertSpecial.value=true
            }) {
                Text(text = "Special AlertDialog")
            }
            */


          /*  if (alertSpecial.value) {
                AlertDialog(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.White,
                    iconContentColor = Color.Red,
                    textContentColor = Color.White,
                    onDismissRequest = { alertSpecial.value = false },
                    title = { Text(text = "Getir Yemek") },
                    text = {
                        TextField(
                            value = tf.value,
                            onValueChange = { tf.value = it },
                            label = { Text(text = "Search") },
                        )

                    },
                    confirmButton = {
                        Text(
                            text = "Select",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                alertDefault.value = false
                                Log.e("Message", "Select: ${tf.value}")
                                tf.value = ""
                            })
                    },
                    dismissButton = {

                        Text(
                            text = "Cancel",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                alertSpecial.value = false
                                Log.e("Message", "Cancel")
                                tf.value = ""
                            })

                    }

                )
            }

            */
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AiNotesTheme {
        ChatPage()
    }
}