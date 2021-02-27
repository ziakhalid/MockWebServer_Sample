package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import android.content.Context
import com.mobiata.mocke3.FileOpener
import java.io.InputStream

class AndroidFileOpener(private val context: Context) : FileOpener{
    override fun openFile(filename: String): InputStream {
        return context.resources.assets.open(filename)
    }
}