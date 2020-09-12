package com.rssreader.ui.channels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rssreader.databinding.FragmentMyChannelsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyChannelsFragment : Fragment() {

    private lateinit var binding: FragmentMyChannelsBinding

    val viewModel by viewModels<MyChannelsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyChannelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.suggestedChannels.observe(viewLifecycleOwner, Observer { channelList ->
            channelList?.let {
                Log.d(
                    "MyChannelsFragment",
                    "Observing suggestedChannels. List size = " + channelList.size
                )
            }
        })
    }


}