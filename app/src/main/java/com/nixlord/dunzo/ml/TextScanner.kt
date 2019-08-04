package com.nixlord.dunzo.ml

import android.Manifest
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.nixlord.dunzo.util.DataFusion
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.extensions.logDebug
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.File

const val THRESHOLD = 70

object TextScanner {

    val detector = FirebaseVision.getInstance().onDeviceTextRecognizer


    val firstMarkers = arrayListOf<String>("item", "name", "particulars", "price", "qty", "total", "amount")
    val secondMarkers = arrayListOf<String>("grand", "total", "subtotal", "vat")

//    fun getElements(
//        firebaseText: FirebaseVisionText
//    ) : ArrayList<String> {
//
//        val elements = arrayListOf<String>()
//        firebaseText.textBlocks.forEach {
//            it.lines.forEach {
//                it.elements.forEach {
//                    elements.add(it.text.toLowerCase())
//                }
//            }
//        }
//        return elements
//    }

    fun parts(lines: ArrayList<String>)  : Pair<HashMap<String, Int>, HashMap<String, Int>> {

        val mapFirstMarkers = hashMapOf<String, Int>()
        val mapSecondMarkers = hashMapOf<String, Int>()

        firstMarkers.forEach { mapFirstMarkers[it] = -1 }
        secondMarkers.forEach { mapSecondMarkers[it] = -1 }

        fun assignClosestMatch(
            map: HashMap<String, Int>,
            target : String,
            currentWord: String,
            currentIndex: Int
        )
        {
            val match = FuzzySearch.ratio(target, currentWord)
            if (match > THRESHOLD) {
                map[target]?.let { index ->
                    if (index == -1)
                        map[target] = currentIndex
                    else {
                        //Exists already.
                        val previousWord = lines[index]
                        val oldMatch = FuzzySearch.ratio(previousWord, target)

                        if (match > oldMatch)
                            map[target] = currentIndex
                    }
                }
            }
        }

        lines.mapIndexed { index, word ->
            firstMarkers.forEach { target ->
                assignClosestMatch(mapFirstMarkers, target, word, index)
            }

            secondMarkers.forEach { target ->
                assignClosestMatch(mapSecondMarkers, target, word, index)
            }
        }

        return Pair(mapFirstMarkers, mapSecondMarkers)
    }
//
//
//
//    fun scan(activity: BaseActivity, image : File) {
//        val bitmap = BitmapFactory.decodeFile(image.path)
//        val firebaseImage = FirebaseVisionImage.fromBitmap(bitmap)
//        detector.processImage(firebaseImage)
//            .addOnSuccessListener {
//                //logDebug(it.text)
//                DataFusion.createProduct(it)
//                parts(it)
//            }
//    }
}