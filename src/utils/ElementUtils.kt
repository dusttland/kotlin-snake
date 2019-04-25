package utils

import org.w3c.dom.Element

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
