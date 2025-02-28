package com.example.notesworkreply.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesworkreply.model.Flags
import com.example.notesworkreply.useCase.GetFlagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlagsViewModel @Inject constructor(
    private val getFlagsUseCase: GetFlagsUseCase
) : ViewModel(){

    private val _flags = mutableStateOf<List<Flags>>(emptyList())
    val flags: State<List<Flags>> = _flags

    init {
        getFlags()
    }
    private fun getFlags(){
        viewModelScope.launch {
            _flags.value = getFlagsUseCase()
        }
    }

}