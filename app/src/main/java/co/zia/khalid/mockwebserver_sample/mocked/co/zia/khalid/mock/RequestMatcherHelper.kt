package co.zia.khalid.mockwebserver_sample.mocked.co.zia.khalid.mock

import java.util.regex.Pattern

fun doesItMatch(regExp: String, str: String): Boolean {
    val pattern = Pattern.compile(regExp)
    val matcher = pattern.matcher(str)
    return matcher.matches()
}