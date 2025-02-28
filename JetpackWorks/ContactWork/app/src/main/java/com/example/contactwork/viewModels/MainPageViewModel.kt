package com.example.contactwork.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.contactwork.model.Persons
import com.example.contactwork.repository.PersonDaoRepo

class MainPageViewModel(application: Application) : AndroidViewModel(application)  {

    var personList = MutableLiveData<List<Persons>>()
    val pRepo = PersonDaoRepo(application)

    init {
        allPeople()
        personList = pRepo.getLiveData()
    }


    fun searchPerson(searchedPerson:String){
        pRepo.searchPerson(searchedPerson)
    }
    fun deletePerson(personID:String){
        pRepo.deletePerson(personID)
    }
    fun allPeople(){
        pRepo.allPeople()
    }

}