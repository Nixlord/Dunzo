package com.nixlord.dunzo.fragments

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.nixlord.dunzo.MainActivity
import com.nixlord.dunzo.R
import com.nixlord.dunzo.azure.ComputerVision
import com.nixlord.dunzo.azure.SpellCheck
import com.nixlord.dunzo.ml.TextScanner
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.extensions.logDebug
import com.phoenixoverlord.pravega.extensions.logError
import kotlinx.android.synthetic.main.fragment_new_product.*
import java.io.File

class AddItemFragment : Fragment() {
    lateinit var imageFile : File

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraButton.setOnClickListener {
            activity.apply {
                (activity as MainActivity).withPermissions(
                    Manifest.permission.CAMERA
                ).execute {
                    (activity as MainActivity).takePhoto("Select Bill")
                        .addOnSuccessListener {
                            it.forEach { image ->
                                imageFile = image
                                ComputerVision.recognize(image, {
                                    logDebug(it)
                                }, {
                                    logError(it)
                                })
                                productImage.setImageBitmap(BitmapFactory.decodeFile("$image"))
                            }
                        }
                }
            }
        }

        azureSpellCheck.setOnClickListener {
            SpellCheck.predict("kachor")
        }

        setupSpinner(productType, R.array.types) { selected -> logDebug(selected) }

        uploadButton.setOnClickListener {
            TextScanner.scan(this.activity as BaseActivity, imageFile)
            /*TODO
                Use the type parameter which has been selected using spinner
             */
        }
    }

    fun setupSpinner(spinner : Spinner, textArrayResID : Int, onItemSelected : (String) -> Unit) {
        ArrayAdapter.createFromResource(
            this.activity as Context,
            textArrayResID,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                logDebug("Nothing Selected")
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(parent?.getItemAtPosition(position) as String)
            }
        }
    }
}