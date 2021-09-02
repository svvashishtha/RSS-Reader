package com.rssreader.ui.itemdescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rssreader.databinding.FragmentFeedItemDescriptionBinding
import com.rssreader.ui.feed.RssFeedFragmentArgs


class FeedItemDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentFeedItemDescriptionBinding
    private val args: FeedItemDescriptionFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedItemDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

}