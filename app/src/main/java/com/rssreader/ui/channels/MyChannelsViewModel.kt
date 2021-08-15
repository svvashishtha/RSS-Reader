package com.rssreader.ui.channels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.ui.firestore.FirestoreArray
import com.firebase.ui.firestore.ObservableSnapshotArray
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rssreader.data.Channel
import com.rssreader.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyChannelsViewModel @Inject constructor(val myChannelRepository: MyChannelRepository) :
    ViewModel() {

    var suggestedChannelsApiResponse = MutableLiveData<ApiResponse<List<Channel>>>()


    fun getChannels() {
        suggestedChannelsApiResponse.value = ApiResponse.loading(null)
        viewModelScope.launch {
            suggestedChannelsApiResponse.value = myChannelRepository.getSuggestedChannels()
        }
    }
}