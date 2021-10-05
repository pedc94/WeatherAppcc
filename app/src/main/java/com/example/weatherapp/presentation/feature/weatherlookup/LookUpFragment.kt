package com.example.weatherapp.presentation.feature.weatherlookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.weatherapp.core.extensions.hideActionBar
import com.example.weatherapp.core.utils.NetworkResult
import com.example.weatherapp.databinding.FragmentLookUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LookUpFragment : Fragment() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private var _binding: FragmentLookUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLookUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).hideActionBar()
        onClickLookUp()
    }

    private fun onClickLookUp() {
        binding.btnLookUp.setOnClickListener {
            if (!binding.etCityName.text.isNullOrBlank()) {
                weatherViewModel.fetchService(binding.etCityName.text.toString().trim())
            } else {
                binding.tfCityName.error = "Can not be empty"
            }
        }
        binding.etCityName.doAfterTextChanged { binding.tfCityName.error = null }
    }

    private fun observeViewModel() {
        weatherViewModel.data.observe(this, { result ->
            result?.let {
                when (result) {
                    is NetworkResult.Success -> {
                        result.data?.let {
                            view?.findNavController()
                                ?.navigate(LookUpFragmentDirections.actionLookUpFragmentToWeatherFragment(
                                    it.toTypedArray()))
                            binding.pbLoadingData.visibility = View.GONE
                        }
                    }
                    is NetworkResult.Loading -> {
                        binding.pbLoadingData.visibility = View.VISIBLE
                    }
                    is NetworkResult.Error -> {
                        binding.pbLoadingData.visibility = View.GONE
                        Snackbar.make(binding.root,
                            "An error occurred while loading data",
                            Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}