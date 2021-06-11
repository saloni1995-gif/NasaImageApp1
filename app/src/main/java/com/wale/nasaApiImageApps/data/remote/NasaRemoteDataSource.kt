package com.wale.nasaApiImageApps.data.remote

import javax.inject.Inject

class NasaRemoteDataSource @Inject constructor(private val apiService: ApiService): BaseDataSource() {
    suspend fun getNasaImages() = getResult { apiService.getAPOD() }
}