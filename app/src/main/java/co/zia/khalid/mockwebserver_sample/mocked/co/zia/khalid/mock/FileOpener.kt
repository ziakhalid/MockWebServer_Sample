package com.mobiata.mocke3

import java.io.IOException
import java.io.InputStream

interface FileOpener {
	@Throws(IOException::class)
	fun openFile(filename: String): InputStream
}
