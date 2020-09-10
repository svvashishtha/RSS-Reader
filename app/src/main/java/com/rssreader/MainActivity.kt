package com.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.rssreader.databinding.ActivityMainBinding
import com.rssreader.util.contentView

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFab()
    }

    private fun setUpFab() {
        // Wrap binding.run to ensure ContentViewBindingDelegate is calling this Activity's
        // setContentView before accessing views
        binding.run {
            findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener(
                this@MainActivity
            )
        }

        // Set a custom animation for showing and hiding the FAB
        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                navigateToCompose()
            }
        }
    }

    private fun navigateToCompose() {
        currentNavigationFragment?.apply {
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            }
        }
        val directions = NavigationGraphDirections.actionAddFeed()
        findNavController(R.id.nav_host_fragment).navigate(directions)
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.addNewFeedFragment -> {
                setUpActivityForAddNewFeedFragment()
            }
            R.id.myFeedsFragment -> {
                setUpActivityForMyFeeds()
            }
            R.id.feedItemDescriptionFragment -> {
            }
            R.id.rssFeedFragment -> {
            }
        }
    }

    private fun setUpActivityForMyFeeds() {
        binding.run{
        binding.fab.show()}
    }

    private fun setUpActivityForAddNewFeedFragment() {
        binding.run{
            binding.fab.hide()
        }
    }
}