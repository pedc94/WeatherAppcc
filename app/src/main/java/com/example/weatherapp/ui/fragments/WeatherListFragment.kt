package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.data.api.model.Forecast
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import com.example.weatherapp.ui.adapter.WeatherListAdapter
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class WeatherListFragment : Fragment() {
    private  var _binding: FragmentWeatherListBinding? = null
    private val weatherViewModel by sharedViewModel<WeatherViewModel>()
    private val forecastAdapter by lazy {WeatherListAdapter(navArgs<WeatherListFragmentArgs>().value.forecast.list, navArgs<WeatherListFragmentArgs>().value.forecast.city.name)}

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = forecastAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            navArgs<WeatherListFragmentArgs>().value.forecast
            (requireActivity()as AppCompatActivity).supportActionBar?.title = navArgs<WeatherListFragmentArgs>().value.forecast.city.name
        }
    }
}