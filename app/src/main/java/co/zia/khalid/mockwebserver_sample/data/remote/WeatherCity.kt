package co.zia.khalid.mockwebserver_sample.data.remote

import com.squareup.moshi.Json

data class WeatherCity(
        val distance: Long,
        val title: String,
        @Json(name = "location_type")
        val locationType: String,
        val woeid: Long,
        @Json(name = "latt_long")
        val lattLong: String
)