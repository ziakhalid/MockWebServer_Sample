package co.zia.khalid.mockwebserver_sample.fake

import co.zia.khalid.mockwebserver_sample.data.Result
import co.zia.khalid.mockwebserver_sample.data.Result.Error
import co.zia.khalid.mockwebserver_sample.data.Result.Success
import co.zia.khalid.mockwebserver_sample.data.WeatherRepository
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherCity
import javax.inject.Inject

class FakeRepository @Inject constructor() : WeatherRepository {

    private var isErrorResponse = false

    private val city1 = getFakeWeatherCity(1836, "Santa Cruz", "Zia City", 2488853, "36.974018,-122.030952")
    private val city2 = getFakeWeatherCity(43722, "San Jose", "Zia City", 2488042, "37.338581,-121.885567")

    override suspend fun getCitiesByLatLong(latLong: String): Result<List<WeatherCity>> {

        if (isErrorResponse) {
            return Error(Exception("Test Error"))
        }
        return Success(listOf(city1, city2))
    }

}

fun getFakeWeatherCity(
    mDistance: Long,
    mTitle: String,
    mLocationType: String,
    mWoeid: Long,
    mLattLong: String
): WeatherCity {
    return WeatherCity(
        distance = mDistance,
        title = mTitle,
        locationType = mLocationType,
        woeid = mWoeid,
        lattLong = mLattLong
    )
}
