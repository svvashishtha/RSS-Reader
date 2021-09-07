package com.rssreader.layman.ui.feed

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.rssreader.layman.R
import com.rssreader.layman.data.FeedItem

class RssFeedItemViewHolder(itemView: View, private val svgDecoder: SvgDecoder) :
    RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<MaterialTextView>(R.id.title)
    private val description = itemView.findViewById<MaterialTextView>(R.id.description)
    private val publishDate = itemView.findViewById<MaterialTextView>(R.id.publish_date)
    private val bodyImage = itemView.findViewById<ImageView>(R.id.body_image)
    fun bind(item: FeedItem, mListener : FeedItemClickListener?) {

        title.text = item.title
        description.text = item.description
        publishDate.text = item.publicationDate
        item.media?.href?.let { url ->
            if (url.endsWith("svg")) {
                bodyImage.load(url) {
                    decoder(svgDecoder)
                }
            } else {
                bodyImage.load(url)
            }
        }
        itemView.setOnClickListener {
            mListener?.openItem(item, itemView)
        }
    }
}