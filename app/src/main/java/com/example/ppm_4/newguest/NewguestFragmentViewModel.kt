package com.example.ppm_4.newguest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.Role
import kotlinx.coroutines.*

class NewguestFragmentViewModel(val database: GuestDatabaseDao) : ViewModel() {

    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    val roles = database.getAllRoles()


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertGuest(role: Any) {
        uiScope.launch {
            insert(role as Role)
        }
    }

    private suspend fun insert(role: Role){
        withContext(Dispatchers.IO) {
            database.insert(Guest(name = name.value?:"", phone = phone.value?:"",email = email.value?:"", role_id = role.Id?: 0))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}