package com.example.ppm_4.database

import androidx.room.Embedded

data class GuestandRole (

    @Embedded
    val guest: Guest,

    val rolName: String,

    val iconIndex: Int
)