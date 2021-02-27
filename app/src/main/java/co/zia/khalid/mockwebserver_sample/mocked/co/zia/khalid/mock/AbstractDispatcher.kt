package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import com.mobiata.mocke3.FileOpener
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse

abstract class AbstractDispatcher (open val fileOpener: FileOpener) : Dispatcher(){

     protected fun getMockResponse(fileName: String, params: Map<String, String>? = null, responseCode: Int = 200): MockResponse {
       return makeResponse(fileName, params, fileOpener, responseCode)
    }

    protected fun throwUnsupportedRequestException(urlPath: String) {
        throw UnsupportedOperationException("Sorry, I don't support the request you passed. (Request:$urlPath)")
    }

}