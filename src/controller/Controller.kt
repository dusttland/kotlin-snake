package controller

import org.w3c.dom.Element
import org.w3c.dom.Node

abstract class Controller(private val rootElement: Element) {

    protected abstract fun view(): Node

    init {
        rootElement.removeChildren()
        rootElement.appendChild(this.view())
        this.onCreate()
    }

    protected abstract fun onCreate()

    protected fun findElement(id: String) = this.rootElement.findElement(id)

}
