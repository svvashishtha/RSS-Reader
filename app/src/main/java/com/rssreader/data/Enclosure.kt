package com.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
class Enclosure constructor(
    @field: Element(name = "url", required = false)
    val url: String?,
    @field: Element(name = "length", required = false)
    val length: String?,
    @field: Element(name = "type", required = false)
    val type: String?){
}