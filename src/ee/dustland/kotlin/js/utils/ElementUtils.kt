package ee.dustland.kotlin.js.utils

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement

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

val HTMLCanvasElement.canvasRenderingContext2D: CanvasRenderingContext2D
    get() = this.getContext("2d") as CanvasRenderingContext2D
