package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import co.zia.khalid.mock.FileOpener
import java.io.InputStream

class AndroidFileOpener(private val context: Context) : FileOpener {
    override fun openFile(filename: String): InputStream {
        return context.resources.assets.open(filename)
    }
}