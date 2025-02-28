package com.example.notificationwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.notificationwork.Notification.NotificationDef
import com.example.notificationwork.Notification.WorkerNotification
import com.example.notificationwork.ui.theme.NotificationWorkTheme
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Blue.toArgb(),
                Color.Blue.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.Red.toArgb(),
                Color.Red.toArgb()
            )
        )
        setContent {
            NotificationWorkTheme {
                MainPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Notification Create")
            })
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(
                onClick = {
                    NotificationDef(context = context)
                }
            ) {
                Text(text = "Notify")
            }
            ElevatedButton(
                onClick = {
                    val constraints = Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                    val work = PeriodicWorkRequestBuilder<WorkerNotification>(15,TimeUnit.MINUTES)
                        .setInitialDelay(15,TimeUnit.SECONDS)
                        .setConstraints(constraints)
                        .build()

                    WorkManager.getInstance(context).enqueue(work)
                }
            ) {
                Text(text = "Worker Notify")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotificationWorkTheme {
        MainPage()
    }
}