package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import com.mobiata.mocke3.FileOpener
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class AppDispatcher(private val fileOpener: FileOpener) : Dispatcher() {

    private val weatherDispatcher = WeatherDispatcher(fileOpener)

    override fun dispatch(request: RecordedRequest): MockResponse {
        if (request.path!!.contains("/api/location/")) {
            return weatherDispatcher.dispatch(request)
        }
        return make404()
    }
}