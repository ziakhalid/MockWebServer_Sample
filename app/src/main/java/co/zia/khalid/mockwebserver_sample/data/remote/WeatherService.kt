package co.zia.khalid.mockwebserver_sample.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/api/location/search")
    suspend fun getCitiesByLatLong(@Query("lattlong") search: String): List<WeatherCity>
}

//"distance": 1836,
//"title": "Santa Cruz",
//"location_type": "City",
//"woeid": 2488853,
//"latt_long": "36.974018,-122.030952"

//https://www.metaweather.com/api/location/search/?lattlong=36.96,-122.02
