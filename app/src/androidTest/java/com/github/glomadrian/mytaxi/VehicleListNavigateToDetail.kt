package com.github.glomadrian.mytaxi

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.glomadrian.mytaxi.vehiclelist.ui.VehicleListContainer
import com.github.glomadrian.mytaxi.vehiclelist.ui.adapter.VehicleAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class VehicleListNavigateToDetail {

    @Rule @JvmField val testRule= ActivityTestRule(VehicleListContainer::class.java)

    @Test
    fun shouldNavigateToVehicleMapDetailsWenVehicleOnTouch() {
        onView(withId(R.id.vehicleList))
                .perform(actionOnItemAtPosition<VehicleAdapter.VehicleViewHolder>(0, click()))

        onView(withId(R.id.taxiDriver)).check(matches(isDisplayed()))
        onView(withId(R.id.driverName)).check(matches(isDisplayed()))
        onView(withId(R.id.distance)).check(matches(isDisplayed()))
        onView(withId(R.id.distanceValue)).check(matches(isDisplayed()))
    }
}