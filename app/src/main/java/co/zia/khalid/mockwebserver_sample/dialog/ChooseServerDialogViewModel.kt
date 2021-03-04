package co.zia.khalid.mockwebserver_sample.dialog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.zia.khalid.mockwebserver_sample.Event
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil
import kotlinx.coroutines.launch

class ChooseServerDialogViewModel : ViewModel() {

    private val _mockServerSelected = MutableLiveData<Event<Unit>>()
    val mockServerSelected: LiveData<Event<Unit>> = _mockServerSelected

    private val _productionServerSelected = MutableLiveData<Event<Unit>>()
    val productionServerSelected: LiveData<Event<Unit>> = _productionServerSelected

    fun mockServerSelected() {
        _mockServerSelected.value = Event(Unit)
    }

    fun productionServerSelected() {
        _productionServerSelected.value = Event(Unit)
    }

    fun saveChooseServerInfo(context: Context?, key: String, value: String) {
        viewModelScope.launch {
            SettingsUtil.save(context, key, value)
        }
    }

}