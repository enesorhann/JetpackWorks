package com.example.notesworkreply.repo

import com.example.notesworkreply.model.Flags
import retrofit2.http.GET

interface ApiService {

    @GET("countries")
    suspend fun getAllFlags() : List<Flags>

}