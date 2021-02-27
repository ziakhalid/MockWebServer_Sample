package co.zia.khalid.mockwebserver_sample.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity
import co.zia.khalid.mockwebserver_sample.databinding.WeatherCityListItemBinding

class WeatherCityListAdapter :
    ListAdapter<WeatherCity, WeatherCityListAdapter.ViewHolder>(WeatherCityDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val weatherCityView: WeatherCityListItemBinding) : RecyclerView.ViewHolder(weatherCityView.root) {

        fun bind(item: WeatherCity) {
            weatherCityView.weatherCity = item
            weatherCityView.executePendingBindings()
        }

        companion object {
            fun getInstance(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = WeatherCityListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(view)
            }
        }
    }

}

object WeatherCityDiff : DiffUtil.ItemCallback<WeatherCity>() {
    override fun areItemsTheSame(oldItem: WeatherCity, newItem: WeatherCity): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: WeatherCity, newItem: WeatherCity): Boolean {
        return oldItem == newItem
    }
}