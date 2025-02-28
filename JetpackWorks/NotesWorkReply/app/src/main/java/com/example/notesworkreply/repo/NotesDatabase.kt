package com.example.notesworkreply.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesworkreply.model.Note

@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao() : NotesDao
}