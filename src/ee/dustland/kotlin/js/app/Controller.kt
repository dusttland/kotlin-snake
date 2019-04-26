package ee.dustland.kotlin.js.app

import org.w3c.dom.Element
import org.w3c.dom.Node
import ee.dustland.kotlin.js.utils.*
import kotlin.browser.document

abstract class Controller(containerID: String) {

    private val container: Element = document.getElementById(containerID)
            ?: throw IllegalArgumentException("Can't find a container with id $containerID")


    init {
        this.container.removeChildren()
        this.container.appendChild(this.viewNode())
        this.onCreate()
    }


    protected abstract fun viewNode(): Node

    protected abstract fun onCreate()


    protected fun findElement(id: String) = this.container.findElement(id)

}
