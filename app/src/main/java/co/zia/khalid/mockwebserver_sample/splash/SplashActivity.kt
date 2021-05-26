package co.zia.khalid.mockwebserver_sample.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.zia.khalid.mockwebserver_sample.MainActivity
import co.zia.khalid.mockwebserver_sample.R
import co.zia.khalid.mockwebserver_sample.dialog.ChooseServerDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var chooseServerDialog: ChooseServerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        chooseServerDialog.showDialog({
            goToMainActivity()
        }, supportFragmentManager, "chooseDialog")
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}