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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.R
import com.rssreader.databinding.FragmentRssFeedBinding
import com.rssreader.util.themeColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Shows the RSS feed of a channel selected.
 * */
@AndroidEntryPoint
class RssFeedFragment : Fragment() {

    private lateinit var binding: FragmentRssFeedBinding
    private val args: RssFeedFragmentArgs by navArgs()
    private val channel by lazy { args.channel }
    private val viewModel by viewModels<MyFeedViewModel>()
    var rssFeedListRecyclerView: RecyclerView? = null

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpFeedListRecyclerView()
        binding.run {
            close.setOnClickListener { findNavController().navigateUp() }
        }

    }

    private fun setUpFeedListRecyclerView() {
        rssFeedListRecyclerView?.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        rssFeedListRecyclerView?.adapter = feedAdapter
    }

    private fun setUpViewModel() {
        viewModel.channel.postValue(channel)
        viewModel.channel.observe(viewLifecycleOwner, { channel ->
            binding.channelTitle.text = channel.title
            viewModel.fetchChannelRssFeed(channel)
        })
        viewModel.feed.observe(viewLifecycleOwner, Observer {
            Log.d("RssFeedFragment", "received update")
        })

    }

}