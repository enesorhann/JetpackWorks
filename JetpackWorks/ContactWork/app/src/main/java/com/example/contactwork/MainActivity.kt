package com.example.contactwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactwork.model.Persons
import com.example.contactwork.ui.theme.ContactWorkTheme
import com.example.contactwork.views.MainPage
import com.example.contactwork.views.SavePerson
import com.example.contactwork.views.UpdatePerson
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactWorkTheme {
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
        composable("saveperson"){
            SavePerson(navController)
        }
        composable("updateperson/{person}", arguments = listOf(
            navArgument("person"){type= NavType.StringType}
        )){
            val json = it.arguments?.getString("person")
            val obj = Gson().fromJson(json, Persons::class.java)
            UpdatePerson(navController,person = obj)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactWorkTheme {
    }
}