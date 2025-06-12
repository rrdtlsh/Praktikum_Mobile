package com.example.modul4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul4.repository.MakeupRepository

class MakeupViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakeupViewModel::class.java)) {
            return MakeupViewModel(MakeupRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
