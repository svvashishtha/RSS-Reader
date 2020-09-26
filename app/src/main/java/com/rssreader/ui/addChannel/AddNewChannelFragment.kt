package com.rssreader.ui.addChannel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.R
import com.rssreader.databinding.FragmentAddNewChannelBinding
import kotlinx.android.synthetic.main.fragment_add_new_channel.*


class AddNewChannelFragment : Fragment() {

    companion object {
        const val SOURCE_FAB: Long = 0
        const val SOURCE_BUTTON: Long = 1
    }

    private val args: AddNewChannelFragmentArgs by navArgs()
    private val source by lazy { args.source }
    private lateinit var binding: FragmentAddNewChannelBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewChannelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialContainerTransform().apply {
            if (source == SOURCE_FAB)
                startView = requireActivity().findViewById(R.id.fab)
            else if (source == SOURCE_BUTTON)
                startView = requireActivity().findViewById(R.id.add_new_channel_button)
            endView = add_new_feed_view
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_medium).toLong()
            addTarget(R.id.add_new_feed_view)
        }
        binding.run {
            close.setOnClickListener { findNavController().navigateUp() }
        }

    }
}