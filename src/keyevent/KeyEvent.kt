package keyevent

import geo.Direction
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.document

class KeyEvent(private val listener: KeyEventListener) {

    init {
        document.onkeydown = { event -> this.onEvent(event) }
    }

    private fun onEvent(event: KeyboardEvent) {
        val arrowKeyDirection: Direction? = event.arrowKeyDirection
        if (arrowKeyDirection != null) {
            this.listener.onArrowKey(arrowKeyDirection)
        }
    }

    private val KeyboardEvent.arrowKeyDirection: Direction?
        get() = when (this.keyCode) {
            KeyCode.UP -> Direction.UP
            KeyCode.RIGHT -> Direction.RIGHT
            KeyCode.DOWN -> Direction.DOWN
            KeyCode.LEFT -> Direction.LEFT
            else -> null
        }

}
