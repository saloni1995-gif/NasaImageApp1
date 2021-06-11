package com.wale.nasaApiImageApps.data.repository

import com.example.rickandmorty.utils.performGetOperation
import com.wale.nasaApiImageApps.data.local.NasaImageDao
import com.wale.nasaApiImageApps.data.remote.NasaRemoteDataSource
import javax.inject.Inject

class NasaImageRepository @Inject constructor(private val remoteDataSource: NasaRemoteDataSource,
                                              private val localDataSource:NasaImageDao) {

    fun getNasaImage() = performGetOperation(
        databaseQuery = { localDataSource.getAllImages()},
        networkCall = { remoteDataSource.getNasaImages() },
        saveCallResult = { localDataSource.insert(it) }
    )

}
