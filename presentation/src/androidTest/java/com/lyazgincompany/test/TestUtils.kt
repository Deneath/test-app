package com.lyazgincompany.test

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher


object TestUtils {
    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    fun waitViewShown(matcher: Matcher<View>): Matcher<View> {
        val idlingResource = ViewShownIdlingResource(matcher)
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            onView(matcher).check(matches(isDisplayed()))
        } finally {
            IdlingRegistry.getInstance().unregister(idlingResource)
            return matcher
        }
    }
}
