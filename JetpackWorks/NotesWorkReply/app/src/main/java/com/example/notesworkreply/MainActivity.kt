package com.example.notesworkreply

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.notesworkreply.navigation.AppNavHost
import com.example.notesworkreply.navigation.AppNavigation
import com.example.notesworkreply.ui.theme.NotesWorkReplyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.RED,
                android.graphics.Color.RED
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.RED,
                android.graphics.Color.RED
            )
        )
        setContent {
            NotesWorkReplyTheme {
                    AppNavHost(
                        navController = rememberNavController(),
                        startDestination = AppNavigation.Login.route
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesWorkReplyTheme {

    }
}