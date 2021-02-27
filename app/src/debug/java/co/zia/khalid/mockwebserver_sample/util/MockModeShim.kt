package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import co.zia.khalid.mockwebserver_sample.server.AppMockWebServer
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_PROXY_SERVER_ADDRESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch

object MockModeShim {

    private lateinit var appMockWebServer: AppMockWebServer

    fun initMockWebServer(context: Context) {
        val appContext = context.applicationContext

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    appMockWebServer = AppMockWebServer(appContext)
                    appMockWebServer.start()
                    SettingsUtil.save(context,
                            PREF_PROXY_SERVER_ADDRESS,
                            appMockWebServer.getHostWithPort()
                    )
                } catch (e: Throwable) {
                    throw RuntimeException("Problem waiting for mock web server to start", e)
                }
            }
        }
    }

    fun getDispatcher() = appMockWebServer.getAppDispatcher()

}