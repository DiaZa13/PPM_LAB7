package com.example.ppm_4.guests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao

class GuestsFragmentViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuestsFragmentViewModel::class.java)) {
            return GuestsFragmentViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}