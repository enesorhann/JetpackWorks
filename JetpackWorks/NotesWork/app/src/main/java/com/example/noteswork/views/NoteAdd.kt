package com.example.noteswork.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteswork.R
import com.example.noteswork.viewModels.NotesViewModel

@Composable
fun NoteAdd(viewModel: NotesViewModel = hiltViewModel()) {

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Red,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 15.dp)
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "add"
                )
            }
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(value = title, onValueChange = { title = it })
            TextField(value = text, onValueChange = { text = it })

        }
    }
}


