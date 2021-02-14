package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLookUpBinding
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LookUpFragment : Fragment() {
    private val weatherViewModel by sharedViewModel<WeatherViewModel>()
    private  var _binding: FragmentLookUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLookUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickLookUp()
    }
    private fun onClickLookUp(){
        binding.btnLookUp.setOnClickListener {
            if (!binding.etCityName.text.isNullOrBlank()) {
                weatherViewModel.fetchForecast(binding.etCityName.text.toString().trim())
            }
            else {
                binding.tfCityName.error = "Can not be empty"
            }
        }
        binding.etCityName.doAfterTextChanged { binding.tfCityName.error = null }
    }
    fun observeViewModel(){
        weatherViewModel.forecastList.observe(this, Observer {forecast  ->
            forecast?.let{
                view?.findNavController()?.navigate(LookUpFragmentDirections.actionLookUpFragmentToWeatherFragment(it))
            }
        })
        weatherViewModel.loadError.observe(this, Observer {isError ->
            if (isError){
                Toast.makeText(requireContext(),"An error occured while loading data", Toast.LENGTH_SHORT).show()
            }

        })
        weatherViewModel.loading.observe(this, Observer {isLoading ->
            isLoading?.let{
                binding.pbLoadingData.visibility = if(it) View.VISIBLE else View.GONE
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}