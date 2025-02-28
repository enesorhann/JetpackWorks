package com.example.contactwork.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactwork.viewModels.SavePageViewModel
import com.example.contactwork.viewModels.UpdatePageViewModel

class SavePageFactory(var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavePageViewModel(application) as T
    }
}