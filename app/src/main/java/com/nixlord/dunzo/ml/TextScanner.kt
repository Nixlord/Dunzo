package com.nixlord.dunzo.ml

import android.Manifest
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.nixlord.dunzo.util.DataFusion
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.extensions.logDebug

object TextScanner {
    val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

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
                                }

                        }
                    }
            }
        }
    }
}