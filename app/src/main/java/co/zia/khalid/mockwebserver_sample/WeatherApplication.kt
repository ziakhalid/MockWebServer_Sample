package co.zia.khalid.mockwebserver_sample

import android.app.Application
import co.zia.khalid.mockwebserver_sample.server.EndPointProvider
import co.zia.khalid.mockwebserver_sample.util.InitializeBuildUtil
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(){

    @Inject lateinit var endPointProvider: EndPointProvider

    override fun onCreate() {
        super.onCreate()
        InitializeBuildUtil.initializeMockWebServerIfMockMode(this, endPointProvider)
    }
}