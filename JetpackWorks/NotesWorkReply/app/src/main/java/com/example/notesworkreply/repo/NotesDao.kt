package com.example.notesworkreply.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesworkreply.model.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}