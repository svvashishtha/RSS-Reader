package com.rssreader.layman.ui.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import com.firebase.ui.firestore.FirestoreArray
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.ObservableSnapshotArray
import com.google.android.material.transition.MaterialElevationScale
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rssreader.layman.NavigationGraphDirections
import com.rssreader.layman.R
import com.rssreader.layman.data.Channel
import com.rssreader.layman.databinding.FragmentMyChannelsBinding
import com.rssreader.layman.ui.addChannel.AddNewChannelFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyChannelsFragment : Fragment(), ChannelAdapterListener {

    private lateinit var binding: FragmentMyChannelsBinding

    private val viewModel by viewModels<MyChannelsViewModel>()
    private var popularChannelView: View? = null
    private var popularChannelList: RecyclerView? = null
    private var addNewChannelButton: Button? = null

    @Inject
    lateinit var svgDecoder: SvgDecoder

    var popularChannelAdapter: PopularChannelFireStoreAdapter? = null

    object ChannelsLiveData : LiveData<ObservableSnapshotArray<Channel>>(),
        FirebaseAuth.AuthStateListener {
        init {
            FirebaseAuth.getInstance().addAuthStateListener(this)
        }

        val channelsQuery = FirebaseFirestore.getInstance().collection("channels")
        override fun onAuthStateChanged(auth: FirebaseAuth) {
            value?.removeAllListeners()
            value =
                if (auth.currentUser == null) null else FirestoreArray(channelsQuery) { snapshot ->
                    snapshot.toObject(Channel::class.java)!!
                }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyChannelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseViews(view)
        addNewChannelButton?.setOnClickListener {
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            }
            val addNewChannelTransition = getString(R.string.add_new_channel_transition)
            val extras = FragmentNavigatorExtras(it to addNewChannelTransition)
            val directions =
                NavigationGraphDirections.actionAddFeed(AddNewChannelFragment.SOURCE_BUTTON)
            findNavController().navigate(directions, extras)
        }
        ChannelsLiveData.observe(viewLifecycleOwner, { snapshots ->
//            popularChannelAdapter?.stopListening()
//            lifecycle.removeObserver(popularChannelAdapter)
            if (snapshots == null) {
                //Not signed in
            } else {
                val options = FirestoreRecyclerOptions.Builder<Channel>()
                    .setSnapshotArray(snapshots)
                    .setLifecycleOwner(viewLifecycleOwner)
                    .build()
                popularChannelAdapter = PopularChannelFireStoreAdapter(options, svgDecoder)

                popularChannelList?.adapter = popularChannelAdapter
                popularChannelAdapter?.channelAdapterListener = this
            }
        })
    }


    private fun initialiseViews(view: View) {
        popularChannelView = view.findViewById(R.id.popular_channel_view)
        popularChannelList = view.findViewById(R.id.popular_channels)
        addNewChannelButton = view.findViewById(R.id.add_new_channel_button)
        popularChannelList?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
    }

    companion object {
        const val SPAN_COUNT = 3
    }

    override fun channelClicked(cardView: View, channel: Channel) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
        }
        val channelToFeedTransition = getString(R.string.channel_to_feed_transition)
        val extras = FragmentNavigatorExtras(cardView to channelToFeedTransition)
        val directions = NavigationGraphDirections.channelToFeed(channel)
        findNavController().navigate(directions, extras)
    }


}