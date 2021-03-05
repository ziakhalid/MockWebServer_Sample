package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import co.zia.khalid.mock.AppDispatcher
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import java.io.IOException
import java.net.InetAddress
import javax.net.ssl.SSLSocketFactory

class AppMockWebServer(context: Context) {

    private var mockWebServer: MockWebServer = MockWebServer()
    private var appDispatcher: AppDispatcher

    init {
        mockWebServer.useHttps(getLocalhostSslSocketFactory(), false)
        val fileOpener = AndroidFileOpener(context)
        appDispatcher = AppDispatcher(fileOpener)
        mockWebServer.dispatcher = appDispatcher
    }

    private fun getLocalhostSslSocketFactory(): SSLSocketFactory {
        val localhost = InetAddress.getByName("localhost").canonicalHostName
        val localhostCertificate = HeldCertificate.Builder()
            .addSubjectAlternativeName(localhost)
            .build()
        val serverCertificates = HandshakeCertificates.Builder()
            .heldCertificate(localhostCertificate)
            .build()
        return serverCertificates.sslSocketFactory()
    }

    fun start() {
        try {
            val address = InetAddress.getByName("localhost")
            mockWebServer.start(address, 0)
        } catch (exception: IOException) {
            throw RuntimeException("Failed to init MockWebServer, wut?", exception)
        }
    }

    fun shutDown() {
        try {
            mockWebServer.shutdown()
        } catch (e: Throwable) {
            throw RuntimeException("Failed to shutdown MockWebServer, wut?", e)
        }
    }

    fun getAppDispatcher() = appDispatcher

    fun getHostWithPort(): String {
//        val mockUrl = mockWebServer.url("")
//        return "${mockUrl.host}:${mockUrl.port}"
         val baseUrl: HttpUrl = mockWebServer.url("")
        return baseUrl.toString()
    }

}