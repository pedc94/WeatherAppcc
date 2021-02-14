package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding

class WeatherDetailsFragment : Fragment() {
    private  var _binding: FragmentWeatherDetailsBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity()as AppCompatActivity).supportActionBar?.title = navArgs<WeatherDetailsFragmentArgs>().value.cityName
        binding.txtDegrees.text = navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.main.temp.toString()
        binding.txtFeelsLike.text = navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.main.feels_like.toString()
        binding.txtWeather.text = navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.weather[0].main
        binding.txtWeatherDetail.text = navArgs<WeatherDetailsFragmentArgs>().value.selectedForecast.weather[0].description
    }
}