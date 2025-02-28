package com.example.notesworkreply.views

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notesworkreply.R
import com.example.notesworkreply.model.Note
import com.example.notesworkreply.navigation.AppNavigation
import com.example.notesworkreply.notification.NotificationService
import com.example.notesworkreply.viewModels.NotesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.currentCameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState


@SuppressLint("UnrememberedMutableState")
@Composable
fun NoteDetail(navController: NavController,viewModel: NotesViewModel = hiltViewModel(),note: Note) {

    val notificationService = NotificationService(LocalContext.current)
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }


    LaunchedEffect(key1 = true) {
        title = note.title!!
        text = note.content!!
    }

    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            TextField(value = title, onValueChange = { title = it }, label = { Text(text = "Title")})
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = text, onValueChange = { text = it }, label = { Text(text = "Text")})
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(modifier = Modifier.size(200.dp,50.dp),
                onClick = {
                    val note = Note(title = title, content = text)
                    viewModel.updateNote(note = note)
                    navController.navigate(AppNavigation.NoteList.route)
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Guncelle")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(modifier = Modifier.size(200.dp,50.dp),
                onClick = {
                    notificationService.showNotification(title,text)
                    navController.navigate(AppNavigation.NoteList.route)
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Notify")
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.size(8.dp))

            val atasehir = LatLng(40.9971,29.1007)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(atasehir,15f)
            }
            val properties by remember {
                mutableStateOf(MapProperties(mapType = MapType.HYBRID))
            }
            GoogleMap(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                cameraPositionState = cameraPositionState,
                properties = properties
            ){
                Marker(
                    title = "Atasehir",
                    state = MarkerState(position = atasehir)
                )
            }
        }


    }
}


