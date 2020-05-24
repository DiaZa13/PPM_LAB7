package com.example.ppm_4.newguest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao

class NewguestFragmentViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewguestFragmentViewModel::class.java)) {
            return NewguestFragmentViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 