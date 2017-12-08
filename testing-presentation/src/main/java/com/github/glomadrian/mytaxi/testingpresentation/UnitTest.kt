package com.github.glomadrian.mytaxi.testingpresentation

import com.github.glomadrian.mytaxi.core.BACKGROUND
import com.github.glomadrian.mytaxi.core.MAIN
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class UnitTest {

    @Before
    fun before() {
        initCorotuinesContextForTesting()
        MockitoAnnotations.initMocks(this)
    }

    private fun initCorotuinesContextForTesting() {
        MAIN = TestContext
        BACKGROUND = TestContext
    }
}