package com.example.noteswork.useCase

import com.example.noteswork.model.Note
import com.example.noteswork.repo.NotesRepository
import javax.inject.Inject

class GetOneNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend fun getNoteById(id: Int): Note? = notesRepository.getNoteById(id)
}