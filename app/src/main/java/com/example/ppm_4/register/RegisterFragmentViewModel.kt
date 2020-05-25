package com.example.ppm_4.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.GuestandRole
import kotlinx.coroutines.*

class RegisterFragmentViewModel(val database: GuestDatabaseDao) : ViewModel() {

    val guests = database.getGuestandRole()

    private val _registerComplete = MutableLiveData<Boolean>()
    val registerComplete: LiveData<Boolean>
        get() = _registerComplete

    var guestIndex = 1
        private set

    var totalGuests = 0
        private set

    val actualGuest = MutableLiveData<GuestandRole>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun initialize(guests: List<GuestandRole>) {
        totalGuests = guests.size
        if (guests.isEmpty()) {
            _registerComplete.value = true
        } else {
            actualGuest.value = guests[guestIndex - 1]
        }
    }

    fun updateRegisteredCurrentGuest() {
        val guest = actualGuest.value
        guestIndex++
        if (totalGuests >= guestIndex) {
            actualGuest.value = guests.value?.get(guestIndex - 1)

        } else {
            _registerComplete.value = true
        }
        uiScope.launch {
            if (guest != null) {
                update(Guest(Id = guest.guest.Id, name = guest.guest.name, phone = guest.guest.phone, email = guest.guest.email, registered = true, role_id = guest.guest.role_id))
            }
        }
    }

    fun updateNotRegisteredCurrentGuest() {
        val guest = actualGuest.value
        guestIndex++
        if (totalGuests >= guestIndex) {
            actualGuest.value = guests.value?.get(guestIndex - 1)
        } else {
            _registerComplete.value = true
        }
    }

    private suspend fun update(guest: Guest) {
        withContext(Dispatchers.IO) {
            guest?.let {
                //it.answer = answer.value ?: ""
                database.update(it)
            }
        }
    }

    fun finishRegister() {
        _registerComplete.value = false
        guestIndex = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}