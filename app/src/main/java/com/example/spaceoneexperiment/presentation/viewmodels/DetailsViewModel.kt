package com.example.spaceoneexperiment.presentation.viewmodels

import android.app.Application
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

class DetailsViewModel(aircraft: Aircraft, application: Application) : ViewModel() {
    private val _aircraft = MutableLiveData<Aircraft>()

    val aircraft: LiveData<Aircraft>
        get() = _aircraft

    private var _navigateOverviewFragment = MutableLiveData<Boolean?>()

    val navigateOverviewFragment: MutableLiveData<Boolean?>
        get() = _navigateOverviewFragment

    init {
        _aircraft.value = aircraft
    }


    fun deleteAircraft(view : View) {
        SpaceOneApi.retrofitService.deleteAircraft(aircraft.value?.id.toString()).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                _navigateOverviewFragment.value = true
                Log.d("OverviewViewModel", "Success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("OverviewViewModel", t.message.toString())
            }

        })
    }

    fun navigationDone() {
        _navigateOverviewFragment.value = null
    }

}