package com.example.ppm_4.roles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.guests.GuestsFragmentViewModel

class RolesViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RolesViewModel::class.java)) {
            return RolesViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}