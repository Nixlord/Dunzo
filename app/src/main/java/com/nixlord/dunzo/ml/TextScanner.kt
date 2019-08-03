package com.nixlord.dunzo.ml

import android.Manifest
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.nixlord.dunzo.util.DataFusion
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.extensions.logDebug
import java.io.File

object TextScanner {
    val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

    fun scan(activity: BaseActivity, image: File) {
        val firebaseImage = FirebaseVisionImage.fromFilePath(activity, Uri.fromFile(image))
        detector.processImage(firebaseImage)
            .addOnSuccessListener {
                //logDebug(it.text)
                DataFusion.createProduct(it)
            }
    }
}