package com.rssreader.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "source", strict = false)
class Source constructor(
    @field: Element(name = "url", required = false)
    val _url: String,
    @field: Element(name = "text", required = false)
    val __text: String?)