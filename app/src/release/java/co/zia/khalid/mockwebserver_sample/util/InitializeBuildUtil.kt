package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import co.zia.khalid.mockwebserver_sample.server.EndPointProvider

object InitializeBuildUtil {

    fun initializeMockWebServerIfMockMode(context: Context, endPointProvider: EndPointProvider) {
        //do nothing for release build
    }
}