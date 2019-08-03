package com.nixlord.dunzo.azure

import okhttp3.*
import java.io.File
import android.R.string
import android.os.Handler
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import java.io.IOException
import java.lang.Error
import java.lang.Exception
import kotlin.math.log


object ComputerVision {

    val http = OkHttpClient()

    fun predict(image: File) {
        try {
            val request = AzureCV.createPredictRequest(image)
            http.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    logError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    logDebug("ComputerVision", response.body?.string())
                }
            })
        } catch (e: Exception) {
            logError(e)
        }


    }


    fun recognize(image: File, onSuccess: (String) -> Unit, onError: (Error) -> Unit) {
        val httpFirst = OkHttpClient()

        val firstRequest = AzureCV.createRecognitionRequest(image)
        var operation: String? = "ERR"
        var flag = true;

        fun startSecondRequest() {
            Handler().postDelayed({
                if (operation != null) {
                    val secondRequest = Request.Builder()
                        .url(operation!!)
                        .addHeader("Ocp-Apim-Subscription-Key", "cd5d8b8cb8f1498e8ce5ba0863daa03b")
                        .build()

                    httpFirst.newCall(secondRequest)
                        .enqueue(object : Callback {
                            override fun onFailure(call: Call, e: IOException) {
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val result = response.body?.string()
                                logDebug("ComputerVision", result)
                                if (result != null)
                                    onSuccess(result)
                                else
                                    onError(Error("Null"))
                            }
                        })
                } else {
                    if (flag) {
                        startSecondRequest()
                        flag = false
                    }
                    else {
                        val error = Error("Timeout")
                        logError(error)
                        onError(error)
                    }
                }
            }, 10000)
        }

        httpFirst.newCall(firstRequest)
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    logError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 202) {
                        operation = response.header("Operation-Location", operation)
                        logDebug("Operation", operation)
                        if (operation == "ERR") {
                            val error = Error("No Operation")
                            logError(error)
                            onError(error)
                        }
                    }
                    else {
                        logError(Error("Code not 202 ${response.code}"))
                        onError(Error("Code not 202 ${response.code}"))
                    }
                }
            })

        startSecondRequest()



    }
}