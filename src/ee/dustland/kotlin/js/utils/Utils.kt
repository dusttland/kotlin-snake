package ee.dustland.kotlin.js.utils

import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.id
import org.w3c.dom.Node
import kotlin.browser.document

fun emptyDiv(elementID: String? = null): Node = document.create.div {
    if (elementID != null) {
        id = elementID
    }
}
