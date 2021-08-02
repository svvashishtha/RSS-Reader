package com.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class FeedWrapper @JvmOverloads constructor(
    @field: Element(name = "channel", required = false)
    var channel: FeedResponse? = null
)
@Root(name = "channel", strict = false)
class FeedResponse @JvmOverloads constructor(
    @field: Element(name = "title", required = false)
    var title: String? = null,
    @field: ElementList(inline = true, required = false)
    var itemList: List<FeedItem>? = null
)

/*@Path("channel")
    @field:Element(name = "title", required = false)
    @param:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    @Path("channel")
    var description: String? = null,

    /**
     * @return the articleList
     */
    /**
     * @param articleList the articleList to set
     */
    @field:ElementList(name = "item", required = false)
    @param:ElementList(name = "item", required = false)
    @Path("channel")
    var articleList: List<FeedItem>? = null*/
