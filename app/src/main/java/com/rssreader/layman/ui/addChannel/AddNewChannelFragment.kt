package com.rssreader.layman.ui.addChannel

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.google.android.material.transition.MaterialContainerTransform
import com.rssreader.layman.R
import com.rssreader.layman.databinding.FragmentAddNewChannelBinding
import com.rssreader.layman.network.ApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_new_channel.*

@AndroidEntryPoint
class AddNewChannelFragment : Fragment() {

    companion object {
        const val SOURCE_FAB: Long = 0
        const val SOURCE_BUTTON: Long = 1
    }

    private val args: AddNewChannelFragmentArgs by navArgs()
    private val source by lazy { args.source }
    private lateinit var binding: FragmentAddNewChannelBinding
    private val viewModel by viewModels<AddNewChannelViewModel>()
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
            endView = add_new_channel_view
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.rss_reader_motion_duration_medium).toLong()
            addTarget(R.id.add_new_channel_view)
        }
        binding.run {
            close.setOnClickListener { findNavController().navigateUp() }
            addNewChannelButton.setOnClickListener {
                val url = inputUrl.text.toString().trim()
                if (viewModel.isValidUrl(url)) {
                    viewModel.addChannel(url)
                } else {
                    Toast.makeText(it.context, "Invalid url", Toast.LENGTH_LONG).show()
                }
            }
        }
        setUpViewModelVariables()


    }


    private fun setUpViewModelVariables() {
        viewModel.addChannelApiResponse.observe(viewLifecycleOwner, Observer {
            when(it.apiStatus){
                ApiStatus.LOADING ->{}
                ApiStatus.SUCCESS ->{context?.let{
                    Toast.makeText(it, "Channel Successfully added", Toast.LENGTH_LONG).show()
                }}
                ApiStatus.ERROR ->{
                    context?.let{
                        Toast.makeText(it, "Failed to add Channel", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}