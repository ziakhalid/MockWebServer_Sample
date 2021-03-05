package co.zia.khalid.mockwebserver_sample.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.zia.khalid.mockwebserver_sample.data.Result
import co.zia.khalid.mockwebserver_sample.data.WeatherRepository
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherFragmentViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _itemList = MutableLiveData<List<WeatherCity>>()
    val itemList:LiveData<List<WeatherCity>> = _itemList

    fun getWeatherCityList(){
        viewModelScope.launch {
            val result = weatherRepository.getCitiesByLatLong("36.96,-122.02")
            (result as? Result.Success)?.let {
                _itemList.value = it.data ?: emptyList()
            }
        }
    }

}