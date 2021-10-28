package com.example.spaceoneexperiment

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceoneexperiment.models.Aircraft
import com.example.spaceoneexperiment.presentation.AircraftAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : LiveData<List<Aircraft>>?) {
    val adapter = recyclerView.adapter as AircraftAdapter
    if(data != null) {
        adapter.submitList(data.value)
    }
}