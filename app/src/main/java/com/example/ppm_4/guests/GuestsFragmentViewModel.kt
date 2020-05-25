package com.example.ppm_4.guests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.GuestandRole
import java.lang.StringBuilder

class GuestsFragmentViewModel(val database:GuestDatabaseDao):  ViewModel() {

    val guestAndrole = database.getGuestandRole()

    private val _guestClicked = MutableLiveData<Int>()
    val guestClicked: LiveData<Int>
        get() = _guestClicked

    fun onGuestClicked(guestId: Int){
        _guestClicked.value = guestId
    }

    fun onGuestClickedComplete(){
        _guestClicked.value = null
    }



}