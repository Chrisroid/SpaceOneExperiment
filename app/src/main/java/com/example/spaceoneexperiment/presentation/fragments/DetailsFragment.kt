package com.example.spaceoneexperiment.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.spaceoneexperiment.databinding.DetailsFragmentBinding
import com.example.spaceoneexperiment.presentation.viewmodels.DetailsViewModel
import com.example.spaceoneexperiment.presentation.DetailViewModelFactory

class DetailsFragment : Fragment() {

    lateinit var viewModel : DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = DetailsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application

        val aircraft  = DetailsFragmentArgs.fromBundle(requireArguments()).selectedAircraft

        val viewModelFactory = DetailViewModelFactory(aircraft, application)

        viewModel =  ViewModelProvider(
            this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.viewmodel = viewModel


//        binding.viewmodel = ViewModelProvider(
//            this, viewModelFactory).get(DetailsViewModel::class.java)


//        binding.deleteButton.setOnClickListener {
//            this.findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToOverviewFragment())
//        }

        binding.updateButton.setOnClickListener {
            this.findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToUpdateAircraftFragment(viewModel.aircraft.value!!))
        }

        viewModel.navigateOverviewFragment.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToOverviewFragment())
                viewModel.navigationDone()
            }
        })
        return binding.root
    }



}