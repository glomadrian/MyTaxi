package com.github.glomadrian.mytaxi.corepresentation.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.glomadrian.mytaxi.corepresentation.app.MyTaxiApplication
import com.github.glomadrian.mytaxi.corepresentation.di.component.ApplicationComponent

abstract class MyTaxiFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val applicationComponent = (it.applicationContext as MyTaxiApplication).applicationComponent
            doInjection(applicationComponent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(onRequestLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAttachPresenter()
        onViewReady(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachPresenter()
    }

    protected abstract fun doInjection(applicationComponent: ApplicationComponent)

    @LayoutRes
    abstract fun onRequestLayoutResource(): Int

    abstract fun onViewReady(savedInstanceState: Bundle?)

    abstract fun onAttachPresenter()

    abstract fun onDetachPresenter()
}