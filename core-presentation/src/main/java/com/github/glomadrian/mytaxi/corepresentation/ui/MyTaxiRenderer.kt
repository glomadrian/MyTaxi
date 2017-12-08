package com.github.glomadrian.mytaxi.corepresentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class MyTaxiRenderer<T>(parent: ViewGroup) {

    lateinit var view: View

    init {
        initializeView(parent)
    }

    private fun initializeView(parent: ViewGroup) {
        val layoutInflater = LayoutInflater.from(parent.context)
        view = provideView(parent, layoutInflater)
    }

    protected abstract fun provideView(viewGroup: ViewGroup, layoutInflater: LayoutInflater): View

    abstract fun render(viewModel: T)
}
