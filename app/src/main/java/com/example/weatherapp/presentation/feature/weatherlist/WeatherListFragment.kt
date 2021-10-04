package com.example.weatherapp.presentation.feature.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.core.extensions.showActionBar
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import com.example.weatherapp.domain.model.ForecastModel
import com.example.weatherapp.presentation.feature.weatherlist.adapter.WeatherListAdapter

class WeatherListFragment : Fragment() {
    private var _binding: FragmentWeatherListBinding? = null
    private val forecastAdapter by lazy { WeatherListAdapter(::onForecastSelected) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).showActionBar()
    }

    private fun setUpRecyclerView() {
        binding.weatherList.adapter = forecastAdapter.apply {
            submitList(navArgs<WeatherListFragmentArgs>().value.forecast.toList())
        }
        binding.weatherList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            navArgs<WeatherListFragmentArgs>().value.forecast.firstOrNull()?.cityName

    }

    private fun onForecastSelected(forecastModel: ForecastModel) {
        findNavController()
            .navigate(WeatherListFragmentDirections.actionWeatherFragmentToWeatherDetailsFragment(
                forecastModel))
    }
}