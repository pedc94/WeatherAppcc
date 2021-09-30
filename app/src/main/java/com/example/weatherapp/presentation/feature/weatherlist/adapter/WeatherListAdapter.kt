package com.example.weatherapp.presentation.feature.weatherlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.domain.model.ForecastModel

typealias onForecastSelected = (ForecastModel) -> Unit

class WeatherListAdapter(
    val onClick: onForecastSelected,
) : ListAdapter<ForecastModel, WeatherListAdapter.WeatherViewHolder>(WeatherDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemBinding =
            ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class WeatherViewHolder(private val itemBinding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(weather: ForecastModel) {
            itemBinding.txtForecast.text = weather.weatherName
            itemBinding.txtTemperature.text = weather.temperature.toString()
            itemBinding.root.setOnClickListener {
                onClick(weather)
            }
        }
    }

    class WeatherDiff : DiffUtil.ItemCallback<ForecastModel>() {
        override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem.dateTime == newItem.dateTime
        }

        override fun areContentsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
            return oldItem == newItem
        }
    }
}