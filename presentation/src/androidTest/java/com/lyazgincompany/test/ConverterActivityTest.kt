package com.lyazgincompany.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lyazgincompany.test.TestUtils.waitViewShown
import com.lyazgincompany.test.TestUtils.withRecyclerView
import com.lyazgincompany.test.features.converter.presentation.ConverterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ConverterActivityTest {

    @get:Rule
    var activityScenarioRule =
        ActivityScenarioRule<ConverterActivity>(ConverterActivity::class.java)

    @Test
    fun testInput() {
        waitViewShown(withId(R.id.converterRecyclerView))

        onView(
            withRecyclerView(R.id.converterRecyclerView)
                .atPositionOnView(0, R.id.rateEditText)
        )
            .check(matches(withText("1")))
    }
}