package ee.dustland.kotlin.js.keyevent

import ee.dustland.kotlin.geo.Direction
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.window

fun onKeyDown(function: (event: KeyboardEvent) -> Unit) {
    window.onkeydown = { event ->
        function.invoke(event)
    }
}

val KeyboardEvent.arrowKeyDirection: Direction?
    get() = when (this.keyCode) {
        KeyCode.UP -> Direction.UP
        KeyCode.RIGHT -> Direction.RIGHT
        KeyCode.DOWN -> Direction.DOWN
        KeyCode.LEFT -> Direction.LEFT
        else -> null
    }

val KeyboardEvent.character: Char?
    get() = if (this.key.length == 1) {
        this.key[0]
    } else {
        null
    }