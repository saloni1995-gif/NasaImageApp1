package com.wale.nasaApiImageApps.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wale.nasaApiImageApps.data.module.NasaImages

@Dao
interface NasaImageDao {
    @Query("SELECT * FROM nasa")
    fun getAllImages() : LiveData<List<NasaImages>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<NasaImages>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(images: NasaImages)
}