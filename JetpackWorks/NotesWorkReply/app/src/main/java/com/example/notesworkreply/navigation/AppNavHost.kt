package com.example.notesworkreply.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesworkreply.model.Note
import com.example.notesworkreply.views.FlagList
import com.example.notesworkreply.views.LoginPage
import com.example.notesworkreply.views.NoteAdd
import com.example.notesworkreply.views.NoteDetail
import com.example.notesworkreply.views.NoteList
import com.example.notesworkreply.views.RegisterPage
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
){
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(AppNavigation.NoteList.route){
            NoteList(navController)
        }
        composable(AppNavigation.NoteAdd.route){
            NoteAdd(navController)
        }
        composable("${AppNavigation.NoteDetail.route}/{note}"){
            val json = it.arguments?.getString("note") ?: null
            val gson = Gson().fromJson(json,Note::class.java)
            NoteDetail(navController = navController, note = gson )
        }
        composable(AppNavigation.FlagList.route){
            FlagList()
        }
        composable(AppNavigation.Login.route){
            LoginPage(navController)
        }
        composable(AppNavigation.Register.route){
            RegisterPage(navController)
        }
    }
}

