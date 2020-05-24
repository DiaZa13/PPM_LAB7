package com.example.ppm_4.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.Guest
import com.example.ppm_4.database.GuestDatabaseDao
import java.lang.StringBuilder

class ResultsFragmentViewModel(val database: GuestDatabaseDao) : ViewModel() {

    //val guests = database.getAllGuests()

    val tRegistered = database.getRegisteredGuests()
    val tGuests = database.getGuestsCount()

    private val guestsDB = database.getAllGuests()

    val guestText = Transformations.map(guestsDB) {
        buildGuestText(it)
    }

    private fun buildGuestText(guests: List<Guest>): String {
        val guestText = StringBuilder()
        for (guest in guests) {
            guestText.append({"${guest.name}: ${guest.registered}\n"})
        }
        return guestText.toString()
    }
}