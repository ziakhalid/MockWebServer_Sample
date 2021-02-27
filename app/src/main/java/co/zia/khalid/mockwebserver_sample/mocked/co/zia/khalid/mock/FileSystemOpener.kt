package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import com.mobiata.mocke3.FileOpener
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class FileSystemOpener(private val root: String) : FileOpener {
    @Throws(IOException::class)
    override fun openFile(filename: String): InputStream {
        return FileInputStream("$root/$filename")
    }
}
