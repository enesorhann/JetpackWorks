package com.example.cookwork.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookwork.model.Cooks
import com.example.cookwork.repo.CooksDaoRepo

class MainPageViewModel(application: Application) : AndroidViewModel(application) {

    val cRepo = CooksDaoRepo(application)
    var liveList = MutableLiveData<List<Cooks>>()

    init {
        allCooks()
        liveList = cRepo.getLiveData()
    }

    fun allCooks(){
       cRepo.allCooks()
    }
}