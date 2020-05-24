package com.example.ppm_4.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface GuestDatabaseDao {

    @Insert
    fun insert(guest: Guest)

    @Insert
    fun insert(role: Role)

    @Update
    fun update(guest: Guest)


    @Query("SELECT * FROM guest_table ORDER BY Id ASC")
    fun getAllGuests(): LiveData<List<Guest>>

    @Query("SELECT * FROM role_table ORDER BY Id ASC")
    fun getAllRoles(): LiveData<List<Role>>

    @Query("SELECT COUNT(*) FROM guest_table")
    fun getGuestsCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM guest_table WHERE registered = 1 ")
    fun getRegisteredGuests(): LiveData<Int>

    @Query("SELECT g.*, r.rolName FROM guest_table g LEFT JOIN role_table r ON g.role_id = r.Id ORDER BY r.`order`")
    fun getGuestandRole(): LiveData<List<GuestandRole>>


}

