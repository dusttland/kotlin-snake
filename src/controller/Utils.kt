package controller

import kotlinx.html.div
import kotlinx.html.dom.create
import org.w3c.dom.Element
import org.w3c.dom.Node
import kotlin.browser.document

fun Element.findElement(id: String): Element {
    val selector = "#$id"
    return this.querySelector(selector) ?: throw IllegalArgumentException("Element $selector not found.")
}

fun Element.removeChildren() {
    var firstChild = this.firstChild
    while (firstChild != null) {
        this.removeChild(firstChild)
        firstChild = this.firstChild
    }
}

fun emptyDiv(): Node = document.create.div {  }
