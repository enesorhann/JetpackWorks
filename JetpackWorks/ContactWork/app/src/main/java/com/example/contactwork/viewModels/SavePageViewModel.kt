package com.example.contactwork.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.contactwork.repository.PersonDaoRepo

class SavePageViewModel(application: Application) : AndroidViewModel(application)  {

    val pRepo = PersonDaoRepo(application)

    fun savePerson(personName:String,personPhone: String){
        pRepo.savePerson(personName,personPhone)
    }
}