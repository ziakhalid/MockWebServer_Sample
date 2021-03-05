package co.zia.khalid.mockwebserver_sample.di

import android.content.Context
import co.zia.khalid.mockwebserver_sample.data.DefaultWeatherRepository
import co.zia.khalid.mockwebserver_sample.data.WeatherDataSource
import co.zia.khalid.mockwebserver_sample.data.WeatherRepository
import co.zia.khalid.mockwebserver_sample.data.local.WeatherLocalDataSource
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherRemoteDataSource
import co.zia.khalid.mockwebserver_sample.data.remote.WeatherService
import co.zia.khalid.mockwebserver_sample.dialog.ChooseServerDialog
import co.zia.khalid.mockwebserver_sample.server.EndPointProvider
import co.zia.khalid.mockwebserver_sample.server.EndPointProviderImp
import co.zia.khalid.mockwebserver_sample.util.OkHttpClientFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteWeatherDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalWeatherDataSource

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClientFactory(): OkHttpClientFactory {
        return OkHttpClientFactory()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(okHttpClientFactory: OkHttpClientFactory):OkHttpClient{
        return okHttpClientFactory.getOkHttpClient()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        client: OkHttpClient,
        endPointProvider: EndPointProvider
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(endPointProvider.getWeatherEndpointUrl())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    @RemoteWeatherDataSource
    fun provideWeatherRemoteDataSource(weatherService: WeatherService): WeatherDataSource {
        return WeatherRemoteDataSource(weatherService)
    }

    @Singleton
    @Provides
    @LocalWeatherDataSource
    fun provideWeatherLocalDataSource(): WeatherDataSource {
        return WeatherLocalDataSource()
    }

    @Singleton
    @Provides
    fun provideEndPointProvider(@ApplicationContext context: Context): EndPointProvider {
        return EndPointProviderImp(context)
    }

    @Singleton
    @Provides
    fun provideChooseServerDialog(): ChooseServerDialog {
        return ChooseServerDialog()
    }

}

@Module
@InstallIn(SingletonComponent::class)
object TaskRepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(
        @ApplicationModule.RemoteWeatherDataSource remoteDataSource: WeatherDataSource,
        @ApplicationModule.LocalWeatherDataSource localDataSource: WeatherDataSource
    ): WeatherRepository {
        return DefaultWeatherRepository(remoteDataSource, localDataSource)
    }
}

