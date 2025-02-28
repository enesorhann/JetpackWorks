package com.example.noteswork.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteswork.views.NoteAdd
import com.example.noteswork.views.NoteDetail
import com.example.noteswork.views.NoteList

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = AppNavigation.NoteList.route,
    modifier: Modifier = Modifier,
){
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(AppNavigation.NoteList.route){
            NoteList()
        }
        composable(AppNavigation.NoteAdd.route){
            NoteAdd()
        }
        composable("${AppNavigation.NoteDetail.route}/{note}"){
            //NoteDetail()
        }
    }
}

