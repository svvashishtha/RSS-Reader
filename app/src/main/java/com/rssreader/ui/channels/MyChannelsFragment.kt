package com.rssreader.ui.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialElevationScale
import com.rssreader.NavigationGraphDirections
import com.rssreader.R
import com.rssreader.data.Channel
import com.rssreader.databinding.FragmentMyChannelsBinding
import com.rssreader.network.ApiStatus
import com.rssreader.ui.addChannel.AddNewChannelFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyChannelsFragment : Fragment(), PopularChannelAdapter.ChannelAdapterListener {

    private lateinit var binding: FragmentMyChannelsBinding

    private val viewModel by viewModels<MyChannelsViewModel>()
    private var popularChannelView: View? = null
    private var popularChannelList: RecyclerView? = null
    private var addNewChannelButton: Button? = null

    @Inject
    lateinit var popularChannelAdapter: PopularChannelAdapter


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
        setUpViewModelVariables()
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
    }

    private fun setUpViewModelVariables() {
        viewModel.suggestedChannelsApiResponse.observe(viewLifecycleOwner, { apiRespone ->
            when (apiRespone.apiStatus) {
                ApiStatus.LOADING -> {
                    binding.loadingView.visibility = View.VISIBLE
                }
                ApiStatus.SUCCESS -> {
                    binding.loadingView.visibility = View.GONE
                    apiRespone.data?.let { channels ->
                        popularChannelAdapter.submitList(channels as ArrayList<Channel>)
                    }
                }
                ApiStatus.ERROR -> {
                    binding.loadingView.visibility = View.GONE
                    Toast.makeText(context, apiRespone.message, Toast.LENGTH_LONG).show()
                }
            }

        })
        lifecycleScope.launchWhenResumed {
            viewModel.getChannels()
        }
    }

    private fun initialiseViews(view: View) {
        popularChannelView = view.findViewById(R.id.popular_channel_view)
        popularChannelList = view.findViewById(R.id.popular_channels)
        addNewChannelButton = view.findViewById(R.id.add_new_channel_button)
        popularChannelList?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        popularChannelList?.adapter = popularChannelAdapter
        popularChannelAdapter.channelAdapterListener = this
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