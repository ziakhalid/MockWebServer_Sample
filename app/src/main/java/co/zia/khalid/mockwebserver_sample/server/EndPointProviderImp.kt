package co.zia.khalid.mockwebserver_sample.server

import android.content.Context
import co.zia.khalid.mockwebserver_sample.BuildConfig
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_PROXY_SERVER_ADDRESS
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_WHICH_API_TO_USE

class EndPointProviderImp(private val context: Context) : EndPointProvider {

    override fun getWeatherEndpointUrl(): String {
        return when (getEndPoint()) {
            EndPoint.MOCK_MODE -> getCustomServerAddress()
            EndPoint.PRODUCTION -> "https://www.metaweather.com"
        }
    }

    override fun getEndPoint(): EndPoint {
        if (BuildConfig.RELEASE){
            return EndPoint.PRODUCTION
        }
        return when (getEndPointName()) {
            "Mock Mode" -> EndPoint.MOCK_MODE
            else -> EndPoint.PRODUCTION
        }
    }

    override fun getEndPointName(): String {
        return SettingsUtil.get(context, PREF_WHICH_API_TO_USE)
    }

    override fun getCustomServerAddress(): String {
        //        return "https://$server/"
        return SettingsUtil.get(context, PREF_PROXY_SERVER_ADDRESS, "localhost:3000")
    }

}


