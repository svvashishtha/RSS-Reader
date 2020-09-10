package com.rssreader.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rssreader.databinding.FragmentRssFeedBinding

/**
 * Shows the RSS feed of a channel selected.
 * */
class RssFeedFragment : Fragment() {

    private lateinit var binding: FragmentRssFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

}