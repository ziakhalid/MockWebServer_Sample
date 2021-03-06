package co.zia.khalid.mock

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.*

class WeatherDispatcher(fileOpener: FileOpener) : AbstractDispatcher(fileOpener) {
    override fun dispatch(request: RecordedRequest): MockResponse {
        val urlPath = request.path ?: ""
        var params: MutableMap<String, String> = LinkedHashMap()

        return when{
            WeatherRequestMatcher.isLocationSearchRequest(urlPath) ->{
                return getMockResponse("weather/location_search.json")
            }
            else -> make404()
        }
    }

    class WeatherRequestMatcher {

        companion object {

            fun isLocationSearchRequest(urlPath: String): Boolean {
                return doesItMatch("^/api/location/search.*$", urlPath)
            }

            fun isLocationRequest(urlPath: String): Boolean {
                return doesItMatch("^/api/location.*$", urlPath)
            }
        }

    }
}