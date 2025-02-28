package com.example.pagetransitionlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pagetransitionlearn.navigation.AppNavHost
import com.example.pagetransitionlearn.navigation.AppNavigation
import com.example.pagetransitionlearn.ui.theme.PageTransitionLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PageTransitionLearnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding),
                        startDestination = AppNavigation.Login.route
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun LoginPage(navController: NavController) {
    
    Column(Modifier.padding(16.0.dp)) {
        Text(text = "Login Page")
        Button(onClick = {
            navController.navigate(AppNavigation.Register.route)
        }) {
            Text(text = "Go to Register Page")
        }
    }
}


@Composable
fun RegisterPage(navController: NavController) {
    Column(Modifier.padding(16.0.dp)) {
        Text(text = "Register Page")
        Button(onClick = {
            navController.navigate(AppNavigation.Login.route)
        }) {
            Text(text = "Go to Login Page")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PageTransitionLearnTheme {
        Greeting("Android")
    }
}