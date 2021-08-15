package com.rssreader.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rssreader.NavigationGraphDirections
import com.rssreader.R
import com.rssreader.databinding.ActivityMainBinding
import com.rssreader.ui.addChannel.AddNewChannelFragment
import com.rssreader.util.contentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    private lateinit var auth: FirebaseAuth
    val TAG = "MainActivity"
    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFab()
        auth = Firebase.auth
        checkIfUserIsAuthorized(auth)

    }

    private fun checkIfUserIsAuthorized(auth: FirebaseAuth) {
        if (auth.currentUser == null) {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            baseContext, "signInAnonymously success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, "signInAnonymously:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
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
        val directions = NavigationGraphDirections.actionAddFeed(AddNewChannelFragment.SOURCE_FAB)
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
                setUpActivityForRssFeesFragment()
            }
        }
    }

    private fun setUpActivityForRssFeesFragment() {
        binding.run {
            binding.fab.hide()
        }
    }

    private fun setUpActivityForMyFeeds() {
        binding.run {
            binding.fab.show()
        }
    }

    private fun setUpActivityForAddNewFeedFragment() {
        binding.run {
            binding.fab.hide()
        }
    }
}