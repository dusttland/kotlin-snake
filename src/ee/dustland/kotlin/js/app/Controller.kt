package ee.dustland.kotlin.js.app

import ee.dustland.kotlin.js.keyevent.KeyEventListener
import ee.dustland.kotlin.js.keyevent.onKeyDown
import org.w3c.dom.Element
import org.w3c.dom.Node
import ee.dustland.kotlin.js.utils.*
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document

abstract class Controller(
        containerID: String
) : KeyEventListener {

    private val container: Element = document.getElementById(containerID)
            ?: throw IllegalArgumentException("Can't find a container with id $containerID")


    init {
        this.container.removeChildren()
        this.container.appendChild(this.viewNode())

        onKeyDown { event -> this.onKeyboardEvent(event) }

        this.onCreate()
    }


    protected abstract fun viewNode(): Node

    protected abstract fun onCreate()


    override fun onKeyboardEvent(event: KeyboardEvent) { }


    protected fun findElement(id: String) = this.container.findElement(id)

}
