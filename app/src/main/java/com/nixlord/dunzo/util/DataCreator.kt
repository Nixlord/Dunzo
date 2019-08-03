package com.nixlord.dunzo.util

import com.google.gson.Gson
import com.nixlord.dunzo.model.RecognitionResult

fun createObject(recognitionResult: RecognitionResult) : ArrayList<String> {
    lateinit var finalResult : ArrayList<String>
    for(result in recognitionResult.lines) {
        val line = result
        finalResult.add(line.text)
    }
    return finalResult
}


fun deserializeText(text : String) : RecognitionResult {
    val gson = Gson()
    val recognitionResult = gson.fromJson(text, RecognitionResult::class.java)
    return recognitionResult
}