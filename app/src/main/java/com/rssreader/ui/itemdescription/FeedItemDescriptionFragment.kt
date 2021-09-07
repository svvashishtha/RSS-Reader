package com.rssreader.ui.itemdescription

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.R
import com.rssreader.data.FeedItem
import com.rssreader.databinding.FragmentFeedItemDescriptionBinding
import com.rssreader.ui.feed.RssFeedFragmentArgs
import com.rssreader.util.themeColor


class FeedItemDescriptionFragment : Fragment() {
    val TAG = "FeedItemDescription"
    private lateinit var binding: FragmentFeedItemDescriptionBinding
    private val args: FeedItemDescriptionFragmentArgs by navArgs()
    private val selectedItem: FeedItem by lazy { args.item }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedItemDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            close.setOnClickListener { findNavController().navigateUp() }
            itemTitle.text = selectedItem.title?:""
            webview.loadUrl(selectedItem.link?:"")
            webview.settings.javaScriptEnabled = true

        }
    }

}