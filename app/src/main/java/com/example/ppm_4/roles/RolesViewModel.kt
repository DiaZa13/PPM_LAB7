package com.example.ppm_4.roles

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.Role
import java.lang.StringBuilder

class RolesViewModel(val database:GuestDatabaseDao): ViewModel() {
    // TODO: Implement the ViewModel

    private val roles = database.getAllRoles()

    val rolesText = Transformations.map(roles) {
        buildGuestText(it)
    }

    private fun buildGuestText(roles: List<Role>) : String{
        val rolesText = StringBuilder()
        for (rol in roles){
            rolesText.append("Rol: ${rol.Id}\nNombre: ${rol.rolName}\nDescripcion: ${rol.description}\n" +
                    "Orden: ${rol.order}\n\n")
        }
        return rolesText.toString()
    }
}
