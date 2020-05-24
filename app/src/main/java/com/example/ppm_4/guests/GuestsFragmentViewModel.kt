package com.example.ppm_4.guests

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.GuestandRole
import java.lang.StringBuilder

class GuestsFragmentViewModel(val database:GuestDatabaseDao):  ViewModel() {

    private val guestAndrole = database.getGuestandRole()

    val guestText = Transformations.map(guestAndrole) {
        buildGuestText(it)
    }

    private fun buildGuestText(guests: List<GuestandRole>) : String{
        val guestText = StringBuilder()
        for (guest in guests){
            guestText.append("Invitado: ${guest.guest.Id}\nNombre: ${guest.guest.name}\nTelefono: ${guest.guest.phone}\nEmail: ${guest.guest.email}\n" +
                    "Rol: ${guest.rolName}\n\n")
        }
        return guestText.toString()
    }


}