package com.example.pagetransitionlearn.navigation

enum class Screen{
    Login,
    Register
}

sealed class AppNavigation(val route:String){
    object  Login:AppNavigation(Screen.Login.name)
    object  Register:AppNavigation(Screen.Register.name)
}