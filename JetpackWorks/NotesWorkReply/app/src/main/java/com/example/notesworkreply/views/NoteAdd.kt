package com.example.notesworkreply.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import com.example.notesworkreply.R
import com.example.notesworkreply.model.Note
import com.example.notesworkreply.navigation.AppNavigation
import com.example.notesworkreply.viewModels.NotesViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log


@Composable
fun NoteAdd(navController: NavController,viewModel: NotesViewModel = hiltViewModel()) {

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },

    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(value = title, onValueChange = { title = it }, label = { Text(text = "Title")})
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = text, onValueChange = { text = it }, label = { Text(text = "Text")})
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(modifier = Modifier.size(200.dp,50.dp),
                onClick = {
                    val note = Note(title = title, content = text)
                    viewModel.insertNote(note = note)
                    CoroutineScope(Dispatchers.Main).launch {
                        saveNote(title = title, content = text)
                    }
                    navController.navigate(AppNavigation.NoteList.route)
            },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
                ) {
                Text(text = "Kaydet")
            }
        }
    }
}

fun saveNote(title:String,content:String) {
    val noteRef = FirebaseFirestore.getInstance().collection("notes")
    val info = HashMap<String,Any>()
    info["title"] = title
    info["content"] = content

    noteRef.add(info).addOnSuccessListener {
        Log.e("Insert","SUCCESS")
    }.addOnFailureListener {
        Log.e("Insert","Failure")
    }
}


