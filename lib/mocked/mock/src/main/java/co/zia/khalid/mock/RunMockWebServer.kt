package co.zia.khalid.mock

import okhttp3.mockwebserver.MockWebServer
import java.io.File
import java.net.InetAddress

// ./gradlew :lib:mocked:mock:run
// To Be used for testing Purpose

object RunMockWebServer {
    @Throws(Throwable::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val server = MockWebServer()

        val root = File("../templates").canonicalPath
        println("Template path: $root")
        val opener = FileSystemOpener(root)

        val dispatcher = AppDispatcher(opener)
        server.dispatcher = dispatcher
        server.start(InetAddress.getByName("0.0.0.0"), 7000)
    }
}
