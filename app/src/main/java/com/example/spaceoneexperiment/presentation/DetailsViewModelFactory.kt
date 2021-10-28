package com.example.spaceoneexperiment.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceoneexperiment.models.Aircraft
import com.example.spaceoneexperiment.presentation.viewmodels.DetailsViewModel

class DetailViewModelFactory(
    private val aircraft: Aircraft,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(aircraft, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}