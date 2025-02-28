package com.example.contactwork.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contactwork.repository.PersonDaoRepo

class UpdatePageViewModel(application: Application) : AndroidViewModel(application) {

    val pRepo = PersonDaoRepo(application)

    fun updatePerson(personID:String,personName:String,personPhone: String){
        pRepo.updatePerson(personID, personName, personPhone)
    }
}