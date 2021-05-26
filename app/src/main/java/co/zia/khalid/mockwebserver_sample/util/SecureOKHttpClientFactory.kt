package co.zia.khalid.mockwebserver_sample.util

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

abstract class SecureOKHttpClientFactory {

    open fun getOkHttpClient(): OkHttpClient {
        val clientBuilder = makeOkHttpClientBuilder()
        return clientBuilder.build()
    }

    private fun makeOkHttpClientBuilder(): OkHttpClient.Builder {
        val clientBuilder = OkHttpClient().newBuilder()
        setupClient(clientBuilder)
        addInterceptors(clientBuilder)
        setupSSLSocketFactoryAndConnectionSpec(clientBuilder, makeSslContext())
        return clientBuilder
    }

    private fun setupClient(client: OkHttpClient.Builder) {
        client.connectTimeout(10L, TimeUnit.SECONDS)
        client.readTimeout(10L, TimeUnit.SECONDS)
    }

    open fun addInterceptors(client: OkHttpClient.Builder) {

    }

    protected open fun setupSSLSocketFactoryAndConnectionSpec(client: OkHttpClient.Builder, sslContext: SSLContext) {
        // do nothing
    }

    protected open fun makeSslContext(): SSLContext {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, null, null)
        return sslContext
    }

}