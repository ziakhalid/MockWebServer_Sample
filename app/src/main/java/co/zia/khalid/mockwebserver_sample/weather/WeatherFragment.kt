package co.zia.khalid.mockwebserver_sample.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.zia.khalid.mockwebserver_sample.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var viewBinding: FragmentWeatherBinding
    private val viewModel by viewModels<WeatherFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentWeatherBinding.inflate(inflater, container, false).apply {
            mViewModel = viewModel
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.lifecycleOwner = this.viewLifecycleOwner
        setUpAdapter()
    }

    private fun setUpAdapter(){
        viewBinding.myList.adapter = WeatherCityListAdapter()
    }

}