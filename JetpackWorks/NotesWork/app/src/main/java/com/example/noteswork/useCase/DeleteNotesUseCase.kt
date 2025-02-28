package com.example.noteswork.useCase

import com.example.noteswork.model.Note
import com.example.noteswork.repo.NotesRepository
import javax.inject.Inject

class DeleteNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) = notesRepository.deleteNote(note)
}