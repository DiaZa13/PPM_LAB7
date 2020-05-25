package com.example.ppm_4.roles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.GuestDatabaseDao

class RolesViewModel(val database:GuestDatabaseDao): ViewModel() {
    // TODO: Implement the ViewModel

    val roles = database.getAllRoles()

    private val _roleClicked = MutableLiveData<Int>()
    val roleClicked: LiveData<Int>
        get() = _roleClicked

    fun onRoleClicked(guestId: Int){
        _roleClicked.value = guestId
    }

    fun onRoleClickedComplete(){
        _roleClicked.value = null
    }
}
