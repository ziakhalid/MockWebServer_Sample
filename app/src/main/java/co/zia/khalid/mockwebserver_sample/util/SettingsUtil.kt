package co.zia.khalid.mockwebserver_sample.util

import android.content.Context
import android.content.SharedPreferences

object SettingsUtil {

    private const val APP_PREFERENCE = "app_preference"
    const val PREF_WHICH_API_TO_USE = "which_api_to_use"
    const val PREF_PROXY_SERVER_ADDRESS = "proxy_server_address"

    private fun getSharedPref(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun save(context: Context?, key: String?, value: String?) {
        val shardPref = getSharedPref(context) ?: return
        with(shardPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun get(context: Context?, key: String?, defaultValue: String = ""): String {
        val shardPref = getSharedPref(context)
        return shardPref?.getString(key, defaultValue) ?: ""
    }

}
