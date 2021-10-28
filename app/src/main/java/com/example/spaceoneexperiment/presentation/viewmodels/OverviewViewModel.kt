package com.example.spaceoneexperiment.presentation.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceoneexperiment.models.Aircraft
import com.example.spaceoneexperiment.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OverviewViewModel : ViewModel() {
    private val _aircrafts = MutableLiveData<List<Aircraft>>()


//   companion object {
//       private val _categories  = MutableLiveData<List<Category>>()
//       val categories : LiveData<List<Category>>
//           get() = _categories
//   }

    private val _navigateToSelectedAircraft = MutableLiveData<Aircraft?>()

    val navigateToSelectedAircraft: MutableLiveData<Aircraft?>
        get() = _navigateToSelectedAircraft

    init {
        geAllAircrafts()
//        geAllCategories()
    }

    val aircrafts: LiveData<List<Aircraft>>
        get() = _aircrafts

    private fun geAllAircrafts() {
        SpaceOneApi.retrofitService.getAllAircrafts().enqueue(object : Callback<List<Aircraft>> {
            override fun onFailure(call: Call<List<Aircraft>>, t: Throwable) {
                Log.d("OverviewViewModel", "Failure - getAllAircrafts()")
            }

            override fun onResponse(call: Call<List<Aircraft>>, response: Response<List<Aircraft>>) {
                _aircrafts.value = response.body()
            }
        })
    }

//    private fun geAllCategories() {
//        SpaceOneApi.retrofitService.getAllCategories().enqueue(object : Callback<List<Category>> {
//            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
//                Log.d("OverviewViewModel", "Failure - getAllCategories()")
//            }
//
//            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
//                _categories.value = response.body()
//                _categories.value?.get(0)?.let { Log.d("OverviewViewModel", it.name) }
//            }
//        })
//    }



    fun displayAircraftDetails(aircraft: Aircraft) {
        _navigateToSelectedAircraft.value = aircraft
    }

    fun displayAircraftDetailsDone() {
        _navigateToSelectedAircraft.value = null
    }




}

