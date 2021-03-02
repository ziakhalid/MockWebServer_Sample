package co.zia.khalid.mock

import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class FileSystemOpener(private val root: String) : FileOpener {
    @Throws(IOException::class)
    override fun openFile(filename: String): InputStream {
        return FileInputStream("$root/$filename")
    }
}
