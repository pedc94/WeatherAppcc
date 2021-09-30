package com.example.weatherapp.presentation.feature.weatherdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding

class WeatherDetailsFragment : Fragment() {
    private var _binding: FragmentWeatherDetailsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.cityName
        binding.txtDegrees.text =
            navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.temperature.toString()
        binding.txtFeelsLike.text =
            navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.feelsLike.toString()
        binding.txtWeather.text =
            navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.weatherName
        binding.txtWeatherDetail.text =
            navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.weatherDescription
    }
}