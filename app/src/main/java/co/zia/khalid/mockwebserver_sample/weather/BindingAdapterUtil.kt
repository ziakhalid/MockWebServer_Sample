package co.zia.khalid.mockwebserver_sample.weather

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity

@BindingAdapter("submitList")
fun RecyclerView.submitList(cityList: List<WeatherCity>?) {
    cityList?.let { cityList ->
        (this.adapter as? WeatherCityListAdapter)?.let {
            it.submitList(cityList)
        }
    }
}