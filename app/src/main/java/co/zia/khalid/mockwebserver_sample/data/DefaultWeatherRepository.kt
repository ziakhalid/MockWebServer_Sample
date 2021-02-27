package co.zia.khalid.mockwebserver_sample.data

import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity

class DefaultWeatherRepository(
    private val remoteWeatherDataSource: WeatherDataSource,
    private val localWeatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getCitiesByLatLong(latLong: String): Result<List<WeatherCity>> {
        return remoteWeatherDataSource.getCitiesByLatLong(latLong)
    }

}