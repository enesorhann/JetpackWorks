package com.example.noteswork.useCase

import com.example.noteswork.model.Note
import com.example.noteswork.repo.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke() : List<Note> = notesRepository.getAllNotes()
}