package com.example.ppm_4.newrole

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.Role
import kotlinx.coroutines.*

class NewroleViewModel(val database: GuestDatabaseDao) : ViewModel() {
    // TODO: Implement the ViewModel

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertRole() {
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert(){
        withContext(Dispatchers.IO) {
            database.insert(Role(rolName = name.value?:"",description = description.value?:"", order = order.value?:""))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
