package com.example.ppm_4.database

import androidx.annotation.Nullable
import androidx.room.Embedded

data class GuestandRole (

    @Embedded
    val guest: Guest,

    val rolName: String,

    @Nullable val iconIndex: Int
)