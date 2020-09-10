package com.rssreader.ui.addChannel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.R
import com.rssreader.databinding.FragmentAddNewChannelBinding
import kotlinx.android.synthetic.main.fragment_add_new_channel.*


class AddNewChannelFragment : Fragment() {
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
            startView = requireActivity().findViewById(R.id.fab)
            endView = add_new_feed_view
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
//            containerColor = requireContext().themeColor(R.attr.colorSurface)
//            startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
//            endContainerColor = requireContext().themeColor(R.attr.colorSurface)
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_medium).toLong()
            addTarget(R.id.add_new_feed_view)
        }

    }
}