package com.example.timerwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.timerwork.ui.theme.TimerWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerWorkTheme {
                    Page()
            }
        }
    }
}

@Composable
fun Page() {

    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val timer = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        var timerDs = appDataStore.read_timer()
        timer.value = ++timerDs
        appDataStore.save_timer(timerDs)
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Timer : ${timer.value}", fontSize = 30.sp, color = Color.Blue, fontWeight = FontWeight.Bold)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimerWorkTheme {
        Page()
    }
}