package com.rssreader.layman.ui.addChannel

import android.util.Patterns
import android.webkit.URLUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rssreader.layman.data.AddChannelRequestBody
import com.rssreader.layman.data.Status
import com.rssreader.layman.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class AddNewChannelViewModel @Inject constructor(val addChannelRepository: AddChannelRepository) :
    ViewModel() {

    val addChannelApiResponse = MutableLiveData<ApiResponse<Status>>()
    fun addChannel(url: String) {
        viewModelScope.launch {
            addChannelApiResponse.value = ApiResponse.loading(null)
            addChannelApiResponse.value = addChannelRepository.addNewChannel(AddChannelRequestBody(url))
        }
    }

    fun isValidUrl(urlString: String?): Boolean {
        try {
            val url = URL(urlString)
            return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches()
        } catch (ignored: MalformedURLException) {
        }
        return false
    }

}
