package co.zia.khalid.mockwebserver_sample.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import co.zia.khalid.mockwebserver_sample.Event
import co.zia.khalid.mockwebserver_sample.EventObserver
import co.zia.khalid.mockwebserver_sample.databinding.ChooseServerDialogBinding
import co.zia.khalid.mockwebserver_sample.util.SettingsUtil.PREF_WHICH_API_TO_USE

class ChooseServerDialog : DialogFragment() {

    private lateinit var viewBinding: ChooseServerDialogBinding
    private val viewModel by viewModels<ChooseServerDialogViewModel>()
    private lateinit var dialogFinishedListener: (Event<Unit>) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ChooseServerDialogBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        viewModel.mockServerSelected.observe(viewLifecycleOwner, EventObserver {
            viewModel.saveChooseServerInfo(context, PREF_WHICH_API_TO_USE, "Mock Mode")
            dismissDialog()
        })

        viewModel.productionServerSelected.observe(viewLifecycleOwner, EventObserver {
            viewModel.saveChooseServerInfo(context, PREF_WHICH_API_TO_USE, "Production")
            dismissDialog()
        })
        return viewBinding.root
    }

    fun showDialog(dialogDismissListener: (Event<Unit>) -> Unit, fm: FragmentManager, tag: String) {
        dialogFinishedListener = dialogDismissListener
        show(fm, tag)
    }

    private fun dismissDialog() {
        dialogFinishedListener(Event(Unit))
        dismiss()
    }
}