package com.example.destination.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.destination.R
import com.example.destination.adapter.DestinationAdapter
import com.example.destination.databinding.FragmentHomeBinding
import com.example.destination.model.Destination
import com.example.destination.utils.Constant.DELAY_MILLIS
import com.example.destination.utils.DataDestinationCallback
import com.example.destination.utils.DestinationData

class HomeFragment : Fragment(R.layout.fragment_home),DataDestinationCallback {

    private val binding : FragmentHomeBinding by viewBinding()
    private lateinit var destinationAdapter: DestinationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        destinationAdapter = DestinationAdapter(this)
        if (activity != null){

            shimmerLoading()
            Handler(Looper.getMainLooper()).postDelayed({
                shimmerDismiss()
                initData()
                initRecyclerView()
            }, DELAY_MILLIS)
        }
        binding.imgProfile.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }
    private fun shimmerLoading(){

        binding.apply {
            shimmer.startShimmer()
            rvDestination.visibility = View.GONE
        }
    }
    private fun shimmerDismiss(){
        binding.apply {
            shimmer.stopShimmer()
            shimmer.setShimmer(null)
            shimmer.visibility = View.GONE
            rvDestination.visibility = View.VISIBLE
        }
    }
    private fun initData(){
        val listData = DestinationData.generateDataDestination()
        destinationAdapter.apply {
            setData(listData)
            notifyDataSetChanged()
        }

    }
    private fun initRecyclerView(){

        with(binding.rvDestination){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = destinationAdapter
        }
    }

    override fun setData(destination: Destination) {
        val extraData = HomeFragmentDirections.actionHomeFragmentToDetailFragment(destination)
        view?.findNavController()?.navigate(extraData)
    }

    override fun onResume() {
        super.onResume()
        binding.shimmer.startShimmer()
    }

    override fun onPause() {
        binding.shimmer.stopShimmer()
        super.onPause()
    }

}