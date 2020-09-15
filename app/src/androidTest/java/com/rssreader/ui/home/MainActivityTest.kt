package com.rssreader.ui.home

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rssreader.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class MainActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)
    @get: Rule
    var hiltRule = HiltAndroidRule(this)
    @Test
    fun check_MyChannelsFragmentIsShownOnStart() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            val navigator = it.findNavController(R.id.nav_host_fragment)
            assertEquals(navigator.currentDestination?.id, R.id.myFeedsFragment)
        }

    }

    @Test
    fun check_FABIsDisplayed() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun check_FabCLickTakesToAddChannelFragment(){

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab)).perform(click())
        scenario.onActivity {
            val navigator = it.findNavController(R.id.nav_host_fragment)
            assertEquals(navigator.currentDestination?.id, R.id.addNewFeedFragment)
        }
    }
}
