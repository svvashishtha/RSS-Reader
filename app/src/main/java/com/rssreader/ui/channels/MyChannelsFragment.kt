package com.rssreader.ui.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rssreader.R
import com.rssreader.data.Channel
import com.rssreader.databinding.FragmentMyChannelsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyChannelsFragment : Fragment() {

    private lateinit var binding: FragmentMyChannelsBinding

    private val viewModel by viewModels<MyChannelsViewModel>()
    private var popularChannelView: View? = null
    private var popularChannelList: RecyclerView? = null
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
    }

    private fun setUpViewModelVariables() {
        viewModel.suggestedChannels.observe(viewLifecycleOwner, { channelList ->
            channelList?.let { channels ->
                popularChannelAdapter?.setData(channels as ArrayList<Channel>)
            }
        })
    }

    private fun initialiseViews(view: View) {
        popularChannelView = view.findViewById(R.id.popular_channel_view)
        popularChannelList = view.findViewById(R.id.popular_channels)
        popularChannelList?.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        popularChannelList?.adapter = popularChannelAdapter
    }

    companion object {
        const val SPAN_COUNT = 3
    }


}