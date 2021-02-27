package co.zia.khalid.mockwebserver_sample.data.remote

import co.zia.khalid.mockwebserver_sample.data.Result
import co.zia.khalid.mockwebserver_sample.data.Result.Success
import co.zia.khalid.mockwebserver_sample.data.Result.Error
import co.zia.khalid.mockwebserver_sample.data.WeatherDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val service: WeatherService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherDataSource {
    override suspend fun getCitiesByLatLong(latLong: String): Result<List<WeatherCity>> =
        withContext(ioDispatcher) {
            try {
                val cityList = service.getCitiesByLatLong(latLong)
                if (cityList.isNotEmpty()) {
                    return@withContext Success(cityList)
                } else {
                    return@withContext Error(Exception("Empty List"))
                }
            } catch (e: Exception) {
                return@withContext Error(e)
            }
        }
}