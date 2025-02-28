package com.example.noteswork.repo

import com.example.noteswork.model.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao)  {
    suspend fun getAllNotes(): List<Note> = notesDao.getAllNotes()

    suspend fun getNoteById(id: Int): Note? = notesDao.getNoteById(id)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    suspend fun insertNote(note: Note) = notesDao.insertNote(note)

    suspend fun updateNote(note: Note) = notesDao.updateNote(note)
}