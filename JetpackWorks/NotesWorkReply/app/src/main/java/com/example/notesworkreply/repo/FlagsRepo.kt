package com.example.notesworkreply.repo

import com.example.notesworkreply.model.Flags
import javax.inject.Inject

class FlagsRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllFlags() : List<Flags> = apiService.getAllFlags()
}