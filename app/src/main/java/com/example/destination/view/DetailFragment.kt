package com.example.destination.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.destination.R
import com.example.destination.databinding.FragmentDetailBinding
import com.example.destination.model.Destination
import com.example.destination.utils.Constant.DELAY_MILLIS

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding : FragmentDetailBinding by viewBinding()
    private val args : DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener{
            (activity as AppCompatActivity?)?.supportActionBar?.show()
            view.findNavController().navigateUp()
        }
        shimmerLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            shimmerDismiss()
            populateDestination(args.detailDestination)
        },DELAY_MILLIS)

    }
    private fun populateDestination(destination: Destination?){

        if (destination != null) {
            with(binding) {
                tvDetailTitle.text = StringBuilder(destination.title)
                tvDetailLocation.text = StringBuilder(destination.location)
                ratingBar.rating = destination.rate
                tvDetailDescription.text = StringBuilder(destination.description)
                detailImage.setImageResource(destination.image)
            }
        }
    }
    private fun shimmerLoading(){
        binding.apply {
            shimmerDetailLayout.startShimmer()
            shimmerDetailLayout.visibility = View.VISIBLE
            tvDetailTitle.visibility = View.GONE
            tvDetailLocation.visibility = View.GONE
            ratingBar.visibility = View.GONE
            tvDetailDescription.visibility = View.GONE
            detailImage.visibility = View.GONE
        }
    }
    private fun shimmerDismiss(){
        binding.apply {
            shimmerDetailLayout.stopShimmer()
            shimmerDetailLayout.setShimmer(null)
            shimmerDetailLayout.visibility = View.GONE
            tvDetailTitle.visibility = View.VISIBLE
            tvDetailLocation.visibility = View.VISIBLE
            ratingBar.visibility = View.VISIBLE
            tvDetailDescription.visibility = View.VISIBLE
            detailImage.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerDetailLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerDetailLayout.stopShimmer()
        super.onPause()
    }
}