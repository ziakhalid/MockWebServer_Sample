package co.zia.khalid.mockwebserver_sample.data.local

import co.zia.khalid.mockwebserver_sample.data.Result
import co.zia.khalid.mockwebserver_sample.data.WeatherDataSource
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity

class WeatherLocalDataSource : WeatherDataSource{
    override suspend fun getCitiesByLatLong(latLong: String): Result<List<WeatherCity>> {
        return Result.Success(emptyList())
    }
}