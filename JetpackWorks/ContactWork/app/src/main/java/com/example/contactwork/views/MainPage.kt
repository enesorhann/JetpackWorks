package com.example.contactwork.views

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.contactwork.R
import com.example.contactwork.factory.MainPageFactory
import com.example.contactwork.viewModels.MainPageViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage(navController: NavController){

    val context = LocalContext.current
    val viewModel:MainPageViewModel = viewModel(
        factory = MainPageFactory(context.applicationContext as Application)
    )
    val peopleList = viewModel.personList.observeAsState(listOf())
    val ifSearched = remember { mutableStateOf(false) }
    val tfSearch = remember { mutableStateOf("") }


    LaunchedEffect(key1 = true) {
        viewModel.allPeople()
    }

    Scaffold(
        topBar =  {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    titleContentColor = Color.White,
                ),
                title =  {

                    if (ifSearched.value){
                        TextField(
                            colors = TextFieldDefaults.colors(
                                disabledIndicatorColor = Color.Transparent,
                                disabledTextColor = Color.White,
                                disabledLabelColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                focusedLabelColor = Color.White,
                                cursorColor = Color.Red,
                                unfocusedTextColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                unfocusedIndicatorColor = Color.White
                            ),
                            value = tfSearch.value,
                            onValueChange = {
                                tfSearch.value=it
                                viewModel.searchPerson(it)
                                            },
                            label = { Text(text = "Search")}
                        )
                    }else{
                        Text(text = "Contact")
                         }
                    },

                actions = {
                    if (ifSearched.value){
                        IconButton(
                            onClick = {
                                ifSearched.value=false
                                tfSearch.value=""
                        } ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_cancel_24),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }else{
                        IconButton(onClick = { ifSearched.value=true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(id = R.color.teal_700),
                contentColor = Color.White,
                onClick = {
                navController.navigate("saveperson")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = ""
                )
            }
        }
    ) {  paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(
                count = peopleList.value!!.count(),
                itemContent = {
                    val person = peopleList.value[it]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 5.dp)
                            .clickable {
                                val objToJson = Gson().toJson(person)
                                navController.navigate("updateperson/${objToJson}")
                            },

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${person.person_name} - ${person.person_phone}")
                            IconButton(
                                onClick = { viewModel.deletePerson(person.person_id!!) },
                                ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_24),
                                    contentDescription = "",
                                    tint = colorResource(id = R.color.teal_700))
                            }
                        }
                    }
                }
            )
        }
    }
}