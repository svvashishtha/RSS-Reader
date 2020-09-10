package com.rssreader.ui.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rssreader.databinding.FragmentMyChannelsBinding


class MyChannelsFragment : Fragment() {

    private lateinit var binding: FragmentMyChannelsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyChannelsBinding.inflate(inflater, container, false)
        return binding.root
    }


}