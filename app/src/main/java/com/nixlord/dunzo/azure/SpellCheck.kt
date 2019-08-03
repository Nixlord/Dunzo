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
import java.io.BufferedReader
import java.io.InputStreamReader


object SpellCheck {
    var host = "https://api.cognitive.microsoft.com"
    var path = "/bing/v7.0/spellcheck"

    var key = "ddfdcc52727e4ebe8afd67ac89b53f65"

    var mkt = "en-US"
    var mode = "proof"
    var text = "Hollo, wrld!"


    @Throws(Exception::class)
    fun check() {
        val params = "?mkt=$mkt&mode=$mode"
        // add the rest of the code snippets here (except prettify() and main())...
        val url = URL(host + path + params)
        val connection = url.openConnection() as HttpsURLConnection

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        connection.setDoOutput(true);

        val wr = DataOutputStream(connection.outputStream)
        wr.writeBytes("text=$text")
        wr.flush()
        wr.close()

        val inp = BufferedReader(
            InputStreamReader(connection.getInputStream())
        );
        var line : String = ""

        while (true) {
            line = inp.readLine()
            if (line == null)
                break
            logDebug("SpellCheck", line)
        }
        inp.close();
    }

    // This function prettifies the json response.
    fun prettify(json_text: String): String {
        val parser = JsonParser()
        val json = parser.parse(json_text)
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(json)
    }

}