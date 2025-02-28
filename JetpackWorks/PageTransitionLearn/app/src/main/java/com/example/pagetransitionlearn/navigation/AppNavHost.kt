package com.example.pagetransitionlearn.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pagetransitionlearn.LoginPage
import com.example.pagetransitionlearn.RegisterPage


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppNavigation.Login.route
){
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
        composable(AppNavigation.Login.route){
            LoginPage(navController)
        }
        composable(AppNavigation.Register.route){
            RegisterPage(navController)
        }
    }
}
