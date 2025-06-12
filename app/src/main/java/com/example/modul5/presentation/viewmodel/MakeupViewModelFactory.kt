package com.example.modul5.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5.data.repository.MakeupRepository

class MakeupViewModelFactory(private val repository: MakeupRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakeupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MakeupViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}