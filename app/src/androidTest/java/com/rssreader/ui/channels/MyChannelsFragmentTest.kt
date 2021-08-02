package com.rssreader.ui.channels

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rssreader.R
import com.rssreader.di.RepositoryModule
import com.rssreader.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MyChannelsFragmentTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var myChannelRepository: MyChannelRepository

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()

    }

    @Test
    fun test_clickOnAddNewChannelButtonOpensAddnewChannelFragment() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.navigation_graph)
        }

        val fragment = launchFragmentInHiltContainer<MyChannelsFragment>()

        Navigation.setViewNavController(fragment.requireView(), navController)


        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.add_new_channel_button)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.addNewFeedFragment)

    }

    @Test
    fun test_PopularChannelsAreDisplayed() {
        val channelList = myChannelRepository.getSuggestedChannels().value
        channelList?.forEach { channel ->
            onView(withText(channel.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun test_ClickOnPopularChannelOpensRssFeedFragment(){
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.navigation_graph)

        val fragment = launchFragmentInHiltContainer<MyChannelsFragment>()

        Navigation.setViewNavController(fragment.requireView(), navController)

        //click on recycler view item
        onView(withId(R.id.popular_channels))
            .perform(actionOnItemAtPosition<PopularChannelViewHolder>(0, click()));
        //see if RssFeedFragment has been opened.
        assertEquals(navController.currentDestination?.id, R.id.rssFeedFragment)
    }
}