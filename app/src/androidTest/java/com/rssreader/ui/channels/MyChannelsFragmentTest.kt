package com.rssreader.ui.channels

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rssreader.R
import com.rssreader.di.RepositoryModule
import com.rssreader.launchFragmentInHiltContainer
import com.rssreader.ui.FakeChannelRepository
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
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
    fun test_AddNewChannelButtonIsDisplayed() {

        launchFragmentInHiltContainer<MyChannelsFragment>()

        onView(withId(R.id.popular_channels)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_PopularChannelsAreDisplayed() {
        val channelList = myChannelRepository.getSuggestedChannels().value
        channelList?.forEach {channel->
            onView(withText(channel.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}