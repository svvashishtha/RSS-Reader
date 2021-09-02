package com.rssreader.ui.feed

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.rssreader.NavigationGraphDirections
import com.rssreader.R
import com.rssreader.data.FeedItem
import com.rssreader.databinding.FragmentRssFeedBinding
import com.rssreader.network.ApiStatus
import com.rssreader.util.themeColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Shows the RSS feed of a channel selected.
 * */
@AndroidEntryPoint
class RssFeedFragment : Fragment(), FeedItemClickListener {

    private var binding: FragmentRssFeedBinding? = null
    private val args: RssFeedFragmentArgs by navArgs()
    private val channel by lazy { args.channel }
    private val viewModel by viewModels<MyFeedViewModel>()

    @Inject
    lateinit var feedAdapter: RssFeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssFeedBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpFeedListRecyclerView()
        binding?.run {
            close.setOnClickListener { findNavController().navigateUp() }
        }

    }

    private fun setUpFeedListRecyclerView() {
        binding?.rssFeedList?.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        feedAdapter.mListener = this
        binding?.rssFeedList?.adapter = feedAdapter
    }

    private fun setUpViewModel() {
        viewModel.channel.postValue(channel)
        viewModel.channel.observe(viewLifecycleOwner, { channel ->
            binding?.channelTitle?.text = channel.title
            viewModel.fetchChannelRssFeed(channel)
        })
        viewModel.feed.observe(viewLifecycleOwner, Observer { feedAPiResponse ->
            when (feedAPiResponse.apiStatus) {
                ApiStatus.ERROR -> {
                    binding?.shimmerViewContainer?.stopShimmer()
                }
                ApiStatus.SUCCESS -> {
                    binding?.shimmerViewContainer?.stopShimmer()
                    binding?.shimmerViewContainer?.hideShimmer()
                    feedAPiResponse.data?.itemList?.let {
                        feedAdapter.submitList(it)
                    }
                }
                ApiStatus.LOADING -> {
                    binding?.shimmerViewContainer?.startShimmer()

                }
            }
            Log.d("RssFeedFragment", "received update")

        })

    }

    override fun openItem(item: FeedItem, itemView: View) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
        }
        val feedToItemDescriptionTransition = getString(R.string.feed_to_description_transition)
        val extras = FragmentNavigatorExtras(itemView to feedToItemDescriptionTransition)
        val directions = NavigationGraphDirections.feedToItem(item)
        findNavController().navigate(directions, extras)
    }

}