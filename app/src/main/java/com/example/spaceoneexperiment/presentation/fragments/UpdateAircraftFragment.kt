package com.example.spaceoneexperiment.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.spaceoneexperiment.databinding.FragmentUpdateAircraftBinding
import com.example.spaceoneexperiment.models.Aircraft
import com.example.spaceoneexperiment.presentation.viewmodels.OverviewViewModel
import com.example.spaceoneexperiment.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateAircraftFragment : Fragment() {

    lateinit var aircraft: Aircraft

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        aircraft = UpdateAircraftFragmentArgs.fromBundle(requireArguments()).aircraft

        val binding = FragmentUpdateAircraftBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.aircraft = aircraft

        binding.nameEditText.addTextChangedListener { text ->
            aircraft.name = text.toString()
        }

        binding.mEditText.addTextChangedListener { text ->
            aircraft.manufacturer = text.toString()
            Log.d("UpdateAircraftFragment", "Manufacturer changed")
        }

        binding.myEditText.addTextChangedListener { text ->
            aircraft.manufacturingYear = text.toString()
            Log.d("UpdateAircraftFragment", "Manufacturing Year changed")
        }

        binding.saveButton.setOnClickListener {
            updateAircraft()
        }

        return binding.root
    }

    private fun updateAircraft() {
        SpaceOneApi.retrofitService.updateItem(aircraft.id, aircraft).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                findNavController().navigate(UpdateAircraftFragmentDirections.actionUpdateAircraftFragmentToOverviewFragment())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("UpdateAircraftFragment", t.message.toString())
            }
        })
    }
}