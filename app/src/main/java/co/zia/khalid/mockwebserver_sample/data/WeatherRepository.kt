package co.zia.khalid.mockwebserver_sample.data

import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity

interface WeatherRepository {
    suspend fun getCitiesByLatLong(latLong: String):Result<List<WeatherCity>>
}