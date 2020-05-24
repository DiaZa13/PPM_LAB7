package com.example.ppm_4.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao

class RegisterFragmentViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterFragmentViewModel::class.java)) {
            return RegisterFragmentViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}