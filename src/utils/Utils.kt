package utils

import kotlinx.html.div
import kotlinx.html.dom.create
import org.w3c.dom.Node
import kotlin.browser.document

fun emptyDiv(): Node = document.create.div {  }
