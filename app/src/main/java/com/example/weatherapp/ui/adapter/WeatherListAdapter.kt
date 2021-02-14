package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.api.model.Forecast
import com.example.weatherapp.data.api.model.ForecastList
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.ui.fragments.LookUpFragmentDirections
import com.example.weatherapp.ui.fragments.WeatherListFragmentDirections

class WeatherListAdapter(val forecastList : List<ForecastList>, val city : String) : RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(var binding: ItemWeatherBinding):RecyclerView.ViewHolder(binding.root)
    init {
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        /*val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)*/
        val itemBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.txtForecast.text = forecastList[position].weather[0].main
        holder.binding.txtTemperature.text = "Temp: ${forecastList[position].main.temp.toString()}"
        holder.binding.root.setOnClickListener{
            Navigation.findNavController(it).navigate(WeatherListFragmentDirections.actionWeatherFragmentToWeatherDetailsFragment(forecastList[position], city))
        }
    }

    override fun getItemCount() = forecastList.size
}