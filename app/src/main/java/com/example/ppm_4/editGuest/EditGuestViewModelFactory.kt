package com.example.ppm_4.editGuest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao

class EditGuestViewModelFactory(private val database: GuestDatabaseDao, private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditGuestViewModel::class.java)) {
            return EditGuestViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}