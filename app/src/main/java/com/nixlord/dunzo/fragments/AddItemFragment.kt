package com.nixlord.dunzo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nixlord.dunzo.R
import com.nixlord.dunzo.ml.TextScanner
import com.phoenixoverlord.pravega.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_new_product.*

class AddItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraButton.setOnClickListener {
            TextScanner.scan(this.activity as BaseActivity)
        }
    }



}