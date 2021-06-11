package com.wale.nasaApiImageApps.data.remote

import com.wale.nasaApiImageApps.data.module.NasaImages
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("apod?api_key=DHHNEDdQp9ZbGGvZl77BPtw0RLQy5OKI3Qw3C0f2")
      suspend fun getAPOD() : Response<NasaImages>
}