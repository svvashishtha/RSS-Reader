package com.rssreader.layman.ui.itemdescription

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.layman.R
import com.rssreader.layman.data.FeedItem
import com.rssreader.layman.databinding.FragmentFeedItemDescriptionBinding
import com.rssreader.layman.util.themeColor


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
            webview.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    // do your handling codes here, which url is the requested url
                    // probably you need to open that url rather than redirect:
                    view.loadUrl(url)
                    return true // then it is not handled by default action
                }
            }
        }
    }

}