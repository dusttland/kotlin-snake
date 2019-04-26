package ee.dustland.kotlin.js.app

import ee.dustland.kotlin.js.utils.emptyDiv
import ee.dustland.kotlin.js.utils.findElement
import ee.dustland.kotlin.js.utils.removeChildren
import org.w3c.dom.Element
import kotlin.browser.document

abstract class App(
        containerID: String
) {
    private val container: Element = document.getElementById(containerID)
            ?: throw IllegalArgumentException("Could not find container with id $containerID")

    private val controllerContainer: Element

    private var controller: Controller


    init {
        this.container.removeChildren()
        this.container.appendChild(emptyDiv(CONTROLLER_CONTAINER_ID))
        this.controllerContainer = this.container.findElement(CONTROLLER_CONTAINER_ID)
        this.controller = this.initialController(CONTROLLER_CONTAINER_ID)
    }


    abstract fun initialController(containerID: String): Controller


    companion object {
        const val CONTROLLER_CONTAINER_ID = "controller"
    }
}
