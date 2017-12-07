package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.github.glomadrian.mytaxi.vehiclemap.R
import kotlinx.android.synthetic.main.taxi_ordered.*

class TaxiOrderedModalFragment : DialogFragment() {

    companion object {
        fun newInstance() = TaxiOrderedModalFragment()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            dialog.window.setLayout(resources.getDimension(R.dimen.order_dialog_width).toInt(),
                    resources.getDimension(R.dimen.order_dialog_height).toInt())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimation()
    }

    private fun initAnimation() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.enter_from_left)
        car.animation = anim
        anim.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            layoutInflater.inflate(R.layout.taxi_ordered, container, false)
}