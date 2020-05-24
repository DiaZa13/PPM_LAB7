package com.example.ppm_4.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ppm_4.database.GuestDatabaseDao

class ResultsFragmentViewModelFactory(private val database: GuestDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsFragmentViewModel::class.java)) {
            return ResultsFragmentViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}