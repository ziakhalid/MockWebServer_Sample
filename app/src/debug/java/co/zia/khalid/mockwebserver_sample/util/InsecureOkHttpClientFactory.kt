package co.zia.khalid.mockwebserver_sample.util

import android.util.Log
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

open class InsecureOkHttpClientFactory : SecureOKHttpClientFactory() {

    override fun addInterceptors(client: OkHttpClient.Builder) {
        super.addInterceptors(client)

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logger)
    }

    override fun setupSSLSocketFactoryAndConnectionSpec(client: OkHttpClient.Builder, sslContext: SSLContext) {
        super.setupSSLSocketFactoryAndConnectionSpec(client, sslContext)
        configureClientToAcceptAnyServer(client)
    }

    override fun makeSslContext(): SSLContext {
        super.makeSslContext()
        val easyTrustManager = arrayOf(getInsecureX509TrustManager())
        val socketContext = SSLContext.getInstance("TLS")
        socketContext.init(null, easyTrustManager, java.security.SecureRandom())
        return socketContext
    }

    private fun configureClientToAcceptAnyServer(client: OkHttpClient.Builder){
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = makeInsecureTrustAllCertificatesTrustManager()

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val ssSocketFactory = sslContext.socketFactory
            client.sslSocketFactory(ssSocketFactory, trustAllCerts[0] as X509TrustManager)

            val hostnameVerifier = HostnameVerifier { _, _ -> true }
            client.hostnameVerifier(hostnameVerifier)

            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .allEnabledCipherSuites()
                    .build()
            client.connectionSpecs(listOf(spec))
        } catch (e: Exception) {
            Log.e("", "Something went wrong and I couldn't setup the okhttp client to support any server", e)
        }

    }

    private fun makeInsecureTrustAllCertificatesTrustManager(): Array<TrustManager> {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        return trustAllCerts
    }

    private fun getInsecureX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<out X509Certificate> {
                return emptyArray()
            }

            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }
        }
    }

}