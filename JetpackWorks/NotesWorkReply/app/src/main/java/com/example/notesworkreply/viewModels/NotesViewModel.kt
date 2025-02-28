package com.example.notesworkreply.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesworkreply.model.Note
import com.example.notesworkreply.useCase.AddNotesUseCase
import com.example.notesworkreply.useCase.DeleteNotesUseCase
import com.example.notesworkreply.useCase.GetNotesUseCase
import com.example.notesworkreply.useCase.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
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

    fun insertNote(note:Note) {
        viewModelScope.launch {
            addNotesUseCase(note = note)
        }
    }

    fun updateNote(note:Note) {
        viewModelScope.launch {
            updateNotesUseCase(note = note)
        }
    }

    fun deleteNote(note:Note) {
        viewModelScope.launch {
            deleteNotesUseCase(note = note)
            getAllNotes()
        }
    }
}

