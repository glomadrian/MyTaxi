package com.github.glomadrian.mytaxi.vehiclemap.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
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