package com.example.cookwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookwork.model.Cooks
import com.example.cookwork.ui.theme.CookWorkTheme
import com.example.cookwork.views.DetailsPage
import com.example.cookwork.views.MainPage
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookWorkTheme {
                PageTransitions()
            }
        }
    }
}

@Composable
fun PageTransitions(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainpage") {

        composable("mainpage"){
            MainPage(navController = navController)
        }
        composable("detailspage/{cook}", arguments = listOf(
            navArgument("cook"){type= NavType.StringType}
        )){
            val json = it.arguments?.getString("cook")
            val obj = Gson().fromJson(json,Cooks::class.java)
            DetailsPage(cook = obj)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() { CookWorkTheme {} }