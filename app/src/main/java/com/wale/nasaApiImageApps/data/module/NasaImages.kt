package com.wale.nasaApiImageApps.data.module

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nasa")
data class NasaImages(
    @NonNull
    @PrimaryKey
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)
