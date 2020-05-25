package com.example.ppm_4.editGuest

import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import kotlinx.coroutines.*

class EditGuestViewModel(val database: GuestDatabaseDao, val id: Int) : ViewModel() {

    val guest = database.getOneGuestandRole(id)

    val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteGuest(){
        uiScope.launch {
            delete()
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO) {
            guest.value?.let { database.delete(it.guest) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}