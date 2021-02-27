package co.zia.khalid.mockwebserver_sample.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/api/location/search")
    suspend fun getCitiesByLatLong(@Query("lattlong") search: String): List<WeatherCity>
}

