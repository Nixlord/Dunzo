package com.nixlord.dunzo.azure

import javax.net.ssl.HttpsURLConnection
import android.R.attr.mode
import android.R.attr.path
import android.R.attr.host
import java.net.URL
import com.google.common.io.Flushables.flush
import java.io.DataOutputStream
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object SpellCheck {

    var host = "https://api.cognitive.microsoft.com"
    var path = "/bing/v7.0/spellcheck"
    var key = "ddfdcc52727e4ebe8afd67ac89b53f65"
    var mkt = "en-US"
    var mode = "proof"
    var text = "Hollo, wrld!"


    val http = OkHttpClient()

    @Throws(Exception::class)
    fun predict(text: String)  {

        val formBody = FormBody.Builder()
            .add("mkt", mkt)
            .add("mode", mode)
            .add("text", text)
            .build()

        val request = Request.Builder()
            .url(host + path)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Ocp-Apim-Subscription-Key", key)
            .post(formBody)
            .build()

        try {
            http.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    logError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string().let { logDebug("SpellCheck", it) }
                }
            })
        } catch (err: Exception) {
            logError(err)
        }

    }

    // This function prettifies the json response.
    fun prettify(json_text: String): String {
        val parser = JsonParser()
        val json = parser.parse(json_text)
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(json)
    }

}