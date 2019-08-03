package com.nixlord.dunzo.util

import com.google.gson.Gson
import com.nixlord.dunzo.model.RecognitionResult
import com.phoenixoverlord.pravega.extensions.logDebug

object DataCreator {
    fun createObject(recognitionResult: RecognitionResult) : ArrayList<String> {
        var finalResult = arrayListOf<String>()

        for(result in recognitionResult.lines) {

            finalResult.add(result.text)
        }

        return finalResult
    }


    fun deserializeText(text : String) : RecognitionResult {
        val gson = Gson()

        val split = text.split("{")
        split.forEach {
            logDebug("Splitted", it)
        }
        val recognitionResult = gson.fromJson(text, RecognitionResult::class.java)
        logDebug("gson", ""+recognitionResult.page)
        logDebug("gson", ""+recognitionResult.lines)
        return recognitionResult
    }
}

