package com.nixlord.dunzo.util

import com.google.gson.Gson
import com.nixlord.dunzo.model.RecognitionResult
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import kotlin.math.log
import java.util.regex.Pattern
import java.util.regex.Pattern.DOTALL



object DataCreator {


    fun splitter(text: String = "") : String? {

        val re1 = "(\"text\":)"    // Double Quote String 1
        val re2 = "(.*?)"    // Non-greedy match on filler
        val re3 = "(,\"words\")"    // Double Quote String 2

        val p = Pattern.compile(re1 + re2 + re3, Pattern.CASE_INSENSITIVE or Pattern.DOTALL)
        val m = p.matcher(text)
        if (m.find()) {
            val string2 = m.group(2)
//            logDebug("InnerText", string2.toString())
            return string2
        }
        return null
    }


    fun deserializeText(text : String) : ArrayList<String> {
        return text
            .split("{")
            .map { splitter(it) }
            .filterNotNullTo(arrayListOf())
    }
}

