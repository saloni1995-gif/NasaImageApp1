package com.wale.nasaApiImageApps.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.wale.nasaApiImageApps.data.repository.NasaImageRepository

class MainViewModel @ViewModelInject constructor(private val repository: NasaImageRepository) : ViewModel() {
    val images = repository.getNasaImage()
    }