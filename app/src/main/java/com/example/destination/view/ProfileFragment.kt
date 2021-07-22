package com.example.destination.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.destination.R
import com.example.destination.databinding.FragmentProfileBinding
import com.example.destination.utils.Constant.DELAY_MILLIS

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding : FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            setNavigationOnClickListener{
                (activity as AppCompatActivity?)?.supportActionBar?.show()
                view.findNavController().navigateUp()
            }
        }
        shimmerLoading()
        Handler(Looper.getMainLooper())
            .postDelayed({
                shimmerDismiss()
            }, DELAY_MILLIS)

    }

    private fun shimmerLoading(){
        binding.apply {
            shimmer.startShimmer()
            shimmer.visibility = View.VISIBLE
            imageProfile.visibility = View.GONE
            tvName.visibility = View.GONE
            tvEmail.visibility = View.GONE
        }
    }
    private fun shimmerDismiss(){
        binding.apply {
            shimmer.stopShimmer()
            shimmer.setShimmer(null)
            shimmer.visibility = View.GONE
            imageProfile.visibility = View.VISIBLE
            tvName.visibility = View.VISIBLE
            tvEmail.visibility = View.VISIBLE
        }
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