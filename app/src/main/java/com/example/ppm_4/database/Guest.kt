package com.example.ppm_4.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.Index
import org.jetbrains.annotations.NotNull

@Entity(tableName = "guest_table",
        foreignKeys = [
            ForeignKey(entity = Role::class, parentColumns = ["Id"], childColumns = ["role_id"], onDelete = SET_DEFAULT)
        ])

data class Guest(
    @PrimaryKey(autoGenerate = true)  var Id: Int = 0,
    @NotNull val name: String,
    @NotNull val phone: String,
    @NotNull val email: String,
    @NotNull var registered: Boolean = false,
    @NotNull val role_id: Int
)

