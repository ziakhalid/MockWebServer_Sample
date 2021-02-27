package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import co.zia.khalid.mockwebserver_sample.server.EndPoint
import co.zia.khalid.mockwebserver_sample.server.EndPointProvider

object InitializeBuildUtil {

    fun initializeMockWebServerIfMockMode(context: Context, endPointProvider: EndPointProvider) {
        if (endPointProvider.getEndPoint() == EndPoint.MOCK_MODE) {
            MockModeShim.initMockWebServer(context)
        }
    }

}