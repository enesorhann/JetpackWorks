package com.example.notesworkreply.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.notesworkreply.R
import com.example.notesworkreply.model.FirestoreNote
import com.example.notesworkreply.model.Note
import com.example.notesworkreply.navigation.AppNavigation
import com.example.notesworkreply.viewModels.NotesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private val auth = FirebaseAuth.getInstance()
private val user = auth.currentUser

@Composable
fun NoteList(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {

    val notes by viewModel.notes

    val db = FirebaseFirestore.getInstance().collection("notes")
    var fireNote by remember {
        mutableStateOf<List<FirestoreNote>>(emptyList())
    }

    db.get().addOnSuccessListener {
        fireNote = it.toObjects(FirestoreNote::class.java)
    }

    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppNavigation.NoteAdd.route)
                },
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

        LazyColumn(Modifier.padding(paddingValues)) {
                if (notes.isEmpty()) {
                    item {
                        Text(text = "No Notes")
                    }
                }
            items(notes.count()) {
                    val note = notes[it]
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .size(150.dp, 50.dp)
                            .clickable {
                                val objToJson = Gson().toJson(note)
                                navController.navigate("${AppNavigation.NoteDetail.route}/$objToJson")
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            Text(text = note.title!!, fontSize = 25.sp)
                            Spacer(modifier = Modifier.size(1.dp))
                            IconButton(onClick = { viewModel.deleteNote(note) }) {
                                Icon(
                                    tint = Color.Red,
                                    modifier = Modifier.size(40.dp),
                                    painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            item {
                Spacer(modifier = Modifier.size(15.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.size(15.dp))
            }
            items(fireNote.count()) {
                val note = fireNote[it]
                Card(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .size(150.dp, 50.dp)

                ) {
                    Row(
                        modifier = Modifier.fillParentMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(text = note.title!!, fontSize = 25.sp)
                        Spacer(modifier = Modifier.size(1.dp))
                        Text(text = note.content!!)
                    }
                }
            }
            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = user?.email.orEmpty()) },
        actions = {
            IconButton(
                onClick = {
                    auth.signOut()
                    navController.navigate(AppNavigation.Login.route)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                    contentDescription = "more"
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}
