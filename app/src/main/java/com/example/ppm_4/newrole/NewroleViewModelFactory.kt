package com.example.ppm_4.newrole

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.newguest.NewguestFragmentViewModel

class NewroleViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewroleViewModel::class.java)) {
            return NewroleViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}