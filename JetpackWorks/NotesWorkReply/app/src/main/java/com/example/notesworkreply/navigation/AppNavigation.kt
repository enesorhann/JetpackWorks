package com.example.notesworkreply.navigation

enum class Screen{
    NOTE_LIST,
    NOTE_DETAIL,
    NOTE_ADD,
    FLAG_LIST,
    LOGIN,
    REGISTER
}


sealed class AppNavigation(val route: String) {
    object NoteList : AppNavigation(Screen.NOTE_LIST.name)
    object NoteAdd : AppNavigation(Screen.NOTE_ADD.name)
    object NoteDetail : AppNavigation(Screen.NOTE_DETAIL.name)
    object FlagList : AppNavigation(Screen.FLAG_LIST.name)
    object Login : AppNavigation(Screen.LOGIN.name)
    object Register : AppNavigation(Screen.REGISTER.name)
}