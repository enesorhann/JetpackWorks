package com.example.noteswork.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteswork.model.Note
import com.example.noteswork.useCase.AddNotesUseCase
import com.example.noteswork.useCase.DeleteNotesUseCase
import com.example.noteswork.useCase.GetNotesUseCase
import com.example.noteswork.useCase.GetOneNoteUseCase
import com.example.noteswork.useCase.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val getOneNoteUseCase: GetOneNoteUseCase,
    private val addNotesUseCase: AddNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase
) : ViewModel() {

    private val _notes = mutableStateOf<List<Note>>(emptyList())
    val notes: State<List<Note>> = _notes

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            _notes.value = getNotesUseCase()
        }
    }

    fun getOneNote(id:Int){
        viewModelScope.launch {

        }
    }

    suspend fun insertNote(note:Note) {
        viewModelScope.launch {
            addNotesUseCase(note = note)
        }
    }

    suspend fun updateNote(note:Note) {
        viewModelScope.launch {
            updateNotesUseCase(note = note)
        }
    }

    suspend fun deleteNote(note:Note) {
        viewModelScope.launch {
            deleteNotesUseCase(note = note)
        }
    }
}

