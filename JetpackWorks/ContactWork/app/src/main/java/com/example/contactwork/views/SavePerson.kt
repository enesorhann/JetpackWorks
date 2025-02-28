package com.example.contactwork.views

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.contactwork.R
import com.example.contactwork.factory.SavePageFactory
import com.example.contactwork.viewModels.SavePageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SavePerson(navController: NavController){

    val tfName = remember { mutableStateOf("") }
    val tfPhone = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: SavePageViewModel = viewModel(
        factory = SavePageFactory(context.applicationContext as Application)
    )

    Scaffold(
        topBar =  {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    titleContentColor = Color.White,
                ),
                title = { Text(text = "Contact") })
        }
    ) {  paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            TextField(value = tfName.value, onValueChange = {tfName.value=it},
                label = { Text(text = "Name")})
            TextField(value = tfPhone.value, onValueChange = {tfPhone.value=it},
                label = { Text(text = "Phone")})
            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    contentColor = Color.White
                ),
                onClick = {
                    viewModel.savePerson(tfName.value,tfPhone.value)
                    navController.popBackStack()
                }) {
                Text(text = "Save Person")
            }
        }
    }
}