package com.github.glomadrian.mytaxi.corepresentation.extensions


import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun FragmentManager.replaceAndCommit(@IdRes container: Int, fragment: Fragment) {
    beginTransaction().replace(container, fragment, fragment::class.java.name).commit()
}