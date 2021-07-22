package com.example.destination.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.databinding.ItemListBinding
import com.example.destination.model.Destination
import com.example.destination.utils.DataDestinationCallback

class DestinationAdapter (private val dataDestinationCallback: DataDestinationCallback): RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {


    private val listDestination = ArrayList<Destination>()

    fun setData(destination: List<Destination>){
        this.listDestination.addAll(destination)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDestination[position])

    }

    override fun getItemCount(): Int = listDestination.size


    inner class ViewHolder (private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(destinationData: Destination){
            binding.apply{
                tvTitle.text = destinationData.title
                tvLocation.text = destinationData.location
                ratingBar.rating = destinationData.rate
                tvDescription.text = destinationData.description
                imageDestination.setImageResource(destinationData.image)

                itemView.setOnClickListener { dataDestinationCallback.setData(destinationData) }
            }
        }
    }
}
