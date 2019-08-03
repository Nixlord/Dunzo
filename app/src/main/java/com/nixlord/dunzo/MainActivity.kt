package com.nixlord.dunzo

import android.os.Bundle
import com.phoenixoverlord.pravega.base.BaseActivity
import com.phoenixoverlord.pravega.toast

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast("Hello World");

    }
}
