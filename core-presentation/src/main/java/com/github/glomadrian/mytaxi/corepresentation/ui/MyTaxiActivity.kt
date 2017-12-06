package com.github.glomadrian.mytaxi.corepresentation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.glomadrian.mytaxi.corepresentation.R
import com.github.glomadrian.mytaxi.corepresentation.extensions.replaceAndCommit

abstract class MyTaxiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mytaxi_container)
        if (savedInstanceState == null) {
            supportFragmentManager.replaceAndCommit(R.id.container, onRequestFragment())
        }
    }

    abstract fun onRequestFragment(): Fragment
}
