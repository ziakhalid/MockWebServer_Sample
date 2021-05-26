package co.zia.khalid.mockwebserver_sample.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.zia.khalid.mockwebserver_sample.MainCoroutineRule
import co.zia.khalid.mockwebserver_sample.data.WeatherRepository
import co.zia.khalid.mockwebserver_sample.fake.FakeRepository
import co.zia.khalid.mockwebserver_sample.getOrAwaitValue
import co.zia.khalid.mockwebserver_sample.observeForTesting
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi

class WeatherFragmentViewModelTest {

    //subject Under Test
    private lateinit var viewModel: WeatherFragmentViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        val fakeRepository = FakeRepository()
        viewModel = WeatherFragmentViewModel(fakeRepository)
    }

    @Test
    fun testSuccessScenario(){
        viewModel.getWeatherCityList()
        viewModel.itemList.observeForTesting {
            assertThat(viewModel.itemList.getOrAwaitValue()).hasSize(2)
            assertThat(viewModel.itemList.value?.get(0)?.title).isEqualTo("Santa Cruz")
        }
    }

}