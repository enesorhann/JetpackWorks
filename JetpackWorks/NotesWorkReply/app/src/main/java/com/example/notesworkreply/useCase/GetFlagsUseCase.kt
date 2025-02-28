package com.example.notesworkreply.useCase


import com.example.notesworkreply.model.Flags
import com.example.notesworkreply.repo.FlagsRepo
import javax.inject.Inject

class GetFlagsUseCase @Inject constructor(private val flagsRepo: FlagsRepo){
    suspend operator fun invoke() : List<Flags> = flagsRepo.getAllFlags()
}