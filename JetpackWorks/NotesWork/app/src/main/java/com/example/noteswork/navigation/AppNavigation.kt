package com.example.noteswork.navigation

enum class Screen{
    NOTE_LIST,
    NOTE_DETAIL,
    NOTE_ADD
}


sealed class AppNavigation(val route: String) {
    object NoteList : AppNavigation(Screen.NOTE_LIST.name)
    object NoteAdd : AppNavigation(Screen.NOTE_ADD.name)
    object NoteDetail : AppNavigation(Screen.NOTE_DETAIL.name)
}