package com.example.spaceoneexperiment.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.spaceoneexperiment.databinding.FragmentAddNewAircraftBinding
import com.example.spaceoneexperiment.models.AircraftDTO
import com.example.spaceoneexperiment.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewAircraftFragment : Fragment() {

    private var aircraft = AircraftDTO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddNewAircraftBinding.inflate(inflater)

        binding.nameEditText.addTextChangedListener { text ->
            aircraft.name = text.toString()
        }

        binding.mEditText.addTextChangedListener { text ->
            aircraft.manufacturer = text.toString()
        }

        binding.myEditText.addTextChangedListener { text ->
            aircraft.manufacturingYear = text.toString()
        }

        binding.addButton.setOnClickListener {
            addAircraft(aircraft)
        }


        return binding.root
    }

    private fun addAircraft(AircraftDto: AircraftDTO) {
        SpaceOneApi.retrofitService.postAircraft(AircraftDto).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                +Log.d("AddNewAircraftFragment - Success", response.message())
                findNavController().navigate(AddNewAircraftFragmentDirections.actionAddNewAircraftFragmentToOverviewFragment())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("AddNewAircraftFragment", t.message.toString())
            }
        })
    }

}