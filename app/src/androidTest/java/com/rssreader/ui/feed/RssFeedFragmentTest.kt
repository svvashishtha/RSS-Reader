package com.rssreader.ui.feed

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.rssreader.R
import com.rssreader.layman.data.Channel
import com.rssreader.layman.di.RepositoryModule
import com.rssreader.launchFragmentInHiltContainer
import com.rssreader.layman.ui.feed.RssFeedFragment
import com.rssreader.layman.util.SuggestedChannelProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
@RunWith(AndroidJUnit4::class)
class RssFeedFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var channel: Channel

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
        channel = SuggestedChannelProvider.getSuggestedChannels()[0]
    }

    @Test
    fun test_clickOnCloseNavigatesUp() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        InstrumentationRegistry.getInstrumentation().runOnMainSync{
        navController.setGraph(R.navigation.navigation_graph)
        }
        val fragmentArgs = Bundle().apply {
            putSerializable("channel", channel)
        }
        val fragment = launchFragmentInHiltContainer<RssFeedFragment>(fragmentArgs = fragmentArgs)


        Navigation.setViewNavController(fragment.requireView(), navController)


        // Verify that performing a click changes the NavController’s state
        Espresso.onView(ViewMatchers.withId(R.id.close)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.myFeedsFragment)
    }

    @Test
    fun test_channelNameIsShownOnThePage() {
        val fragmentArgs = Bundle().apply {
            putSerializable("channel", channel)
        }
        launchFragmentInHiltContainer<RssFeedFragment>(fragmentArgs = fragmentArgs)
        Espresso.onView(ViewMatchers.withText(channel.title)).check(matches(isDisplayed()))
    }

}