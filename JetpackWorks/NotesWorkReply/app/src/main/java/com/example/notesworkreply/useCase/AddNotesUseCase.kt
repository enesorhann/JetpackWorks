package com.example.notesworkreply.useCase


import com.example.notesworkreply.model.Note
import com.example.notesworkreply.repo.NotesRepository
import javax.inject.Inject

class AddNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) = notesRepository.insertNote(note)

}