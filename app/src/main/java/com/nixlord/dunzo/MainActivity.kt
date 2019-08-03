package com.nixlord.dunzo

import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            toast("Hello World")
        }

//        val image = FirebaseVisionImage.fromBitmap();
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
//        val result = detector.processImage(image)
//            .addOnSuccessListener {
//
//            }
    }
}
