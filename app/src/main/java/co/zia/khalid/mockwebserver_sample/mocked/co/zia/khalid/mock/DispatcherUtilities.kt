package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import com.mobiata.mocke3.FileOpener
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.Calendar
import java.util.LinkedHashMap

fun unUrlEscape(str: String): String {
    return str.replace("%20", " ")
}

fun parseYearMonthDay(ymd: String?, hour: Int, minute: Int): Calendar {
    val (year, month, day) = ymd!!.split("-").map { Integer.parseInt(it) }
    val cal = Calendar.getInstance()
    cal.set(year, month - 1, day, hour, minute)
    return cal
}

fun parseHttpRequest(request: RecordedRequest): MutableMap<String, String> {
    if ("GET" == request.method && request.requestLine.contains("?")) {
        var requestLine = request.requestLine.split("?")[1]
        // Replace "HTTP version" from request line.
        requestLine = requestLine.substring(0, requestLine.lastIndexOf(" "))
        return constructParamsFromVarArray(requestLine)
    } else if ("POST" == request.method) {
        return constructParamsFromVarArray(request.body.readUtf8())
    }
    return LinkedHashMap()
}

fun constructParamsFromVarArray(requestStr: String): MutableMap<String, String> {
    val requestVariablePairs = requestStr.split("&")
    val params = LinkedHashMap<String, String>()
    for (pair in requestVariablePairs) {
        if (pair.isBlank()) {
            continue
        }

        val idx = pair.indexOf("=")
        try {
            val key = URLDecoder.decode(pair.substring(0, idx), "UTF-8")
            val value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
            params.put(key, value)
        } catch (e: UnsupportedEncodingException) {
            // ignore - just skip the pair
        }

    }
    return params
}

fun makeEmptyResponse(): MockResponse {
    val resp = MockResponse()
    resp.setResponseCode(200)
    return resp
}

fun make404(): MockResponse {
    return MockResponse().setResponseCode(404)
}

fun makeResponse(filePath: String, params: Map<String, String>?, fileOpener: FileOpener, responseCode: Int = 200): MockResponse {
    var path = filePath
    // Handle all FileOpener implementations
    if (path.startsWith("/")) {
        path = path.substring(1)
    }
    if (path.contains('?')) {
        path = path.substring(0, path.indexOf('?'))
    }

    System.out.println("MockWebSever: " + path)
    val resp = MockResponse()
    try {
        var body = loadMockResponseAndReplaceTemplateParams(path, fileOpener, params)
        resp.setBody(body)
        resp.setHeader("Content-Type", "application/json")
        resp.setResponseCode(responseCode)
    } catch (e: Exception) {
        e.printStackTrace()
        resp.setResponseCode(404)
    }

    return resp
}

fun loadMockResponseAndReplaceTemplateParams(path: String, fileOpener: FileOpener, params: Map<String, String>?): String {
    var body = getResponse(path, fileOpener)
    if (params != null) {
        val it = params.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            val key = "\${" + entry.key + "}"
            if (body.contains(entry.key)) {
                body = body.replace(key, entry.value)
            }
        }
    }
    return body
}

// Read the json responses from the FileOpener
@Throws(IOException::class)
private fun getResponse(filename: String, fileOpener: FileOpener): String {
    val inputStream = fileOpener.openFile(filename)
    val br = BufferedReader(InputStreamReader(inputStream))
    try {
        val sb = StringBuilder()
        var line: String? = br.readLine()

        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    } finally {
        br.close()
    }
}