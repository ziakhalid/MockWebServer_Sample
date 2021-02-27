package co.zia.khalid.mockwebserver_sample.server

import android.content.Context
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_PROXY_SERVER_ADDRESS
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_WHICH_API_TO_USE

class EndPointProviderImp(private val context: Context) : EndPointProvider {

    override fun getWeatherEndpointUrl(): String {
        return when (getEndPoint()) {
            EndPoint.MOCK_MODE -> getCustomServerAddress()
            else -> "https://www.metaweather.com"
        }
    }

    override fun getEndPoint(): EndPoint {
        return when (getEndPointName()) {
            "Mock Mode" -> EndPoint.MOCK_MODE
//            else -> EndPoint.PRODUCTION
            //This is Just Done for testing purpose
            else -> EndPoint.MOCK_MODE
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


