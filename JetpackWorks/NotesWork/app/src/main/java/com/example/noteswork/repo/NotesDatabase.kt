package com.example.noteswork.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteswork.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){
    abstract val notesDao: NotesDao
}