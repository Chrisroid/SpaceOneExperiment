package com.example.spaceoneexperiment.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceoneexperiment.databinding.OverviewFragmentBinding
import com.example.spaceoneexperiment.presentation.viewmodels.OverviewViewModel
import com.example.spaceoneexperiment.presentation.AircraftAdapter

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = AircraftAdapter(AircraftAdapter.OnClickListener {
            viewModel.displayAircraftDetails(it)

        })

        binding.addButton.setOnClickListener {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToAddNewAircraftFragment())
        }

        viewModel.navigateToSelectedAircraft.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it))
                viewModel.displayAircraftDetailsDone()
            }
        })

        return binding.root

    }


}


