package com.nixlord.dunzo

import android.os.Bundle
import com.nixlord.dunzo.ml.TextScanner
import com.phoenixoverlord.pravega.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*fab.setOnClickListener {

            TextScanner.scan(this)
        }*/
    }
}
