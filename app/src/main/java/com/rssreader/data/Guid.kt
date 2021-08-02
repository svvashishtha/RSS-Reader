package com.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "guid", strict = false)
class Guid constructor(
    @field: Element(name = "isPermaLink", required = false)
    val isPermaLink: Boolean?/*,
    @field: Element(name = "text", required = false)
    val text: String?*/) {
}