package com.example.spaceoneexperiment.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spaceoneexperiment.databinding.AircraftItemBinding
import com.example.spaceoneexperiment.models.Aircraft

class AircraftAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Aircraft, AircraftAdapter.AircraftViewHolder>(DiffCallBack) {
    class AircraftViewHolder(private var binding: AircraftItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Aircraft) {
            binding.aircraft = item
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (aircraft: Aircraft) -> Unit) {
        fun onClick(aircraft: Aircraft) = clickListener(aircraft)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Aircraft>() {
        override fun areItemsTheSame(oldItem: Aircraft, newItem: Aircraft): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Aircraft, newItem: Aircraft): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AircraftViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AircraftItemBinding.inflate(layoutInflater, parent, false)
        return AircraftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AircraftViewHolder, position: Int) {
        val aircraft = getItem(position)
        holder.bind(aircraft)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(aircraft)
        }
    }
}