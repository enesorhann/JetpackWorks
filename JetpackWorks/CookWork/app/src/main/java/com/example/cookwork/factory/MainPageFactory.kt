package com.example.cookwork.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookwork.viewModel.MainPageViewModel

class MainPageFactory(var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(application) as T
    }
}