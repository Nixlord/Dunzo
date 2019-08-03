package com.nixlord.dunzo.ml

import android.Manifest
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.nixlord.dunzo.util.DataFusion
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.extensions.logDebug
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.File

object TextScanner {
    val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

    fun parts(firebaseText : FirebaseVisionText) {
        val elements = arrayListOf<String>()

        firebaseText.textBlocks.forEach {
            it.lines.forEach {
                it.elements.forEach {
                    elements.add(it.text)
                }
            }
        }

        val closest = elements.reduce { largest, current ->
            if ( FuzzySearch.ratio(current, "item") > FuzzySearch.ratio(largest, "item"))
                current
            else
                largest
        }

        logDebug("Logging from", "Closest word to item is $closest")
    }

    fun scan(activity: BaseActivity) {
        activity.apply {
            withPermissions(
                Manifest.permission.CAMERA
            ).execute {
                takePhoto("Select Bill")
                    .addOnSuccessListener {
                        it.forEach { image ->
                            val firebaseImage = FirebaseVisionImage.fromFilePath(activity, Uri.fromFile(image))
                            detector.processImage(firebaseImage)
                                .addOnSuccessListener {
                                    //logDebug(it.text)
                                    DataFusion.createProduct(it)
                                    parts(it)
                                }

                        }
                    }
            }
        }
    }
}