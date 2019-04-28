package ee.dustland.kotlin.js.keyevent

import org.w3c.dom.events.KeyboardEvent

interface KeyEventListener {
    fun onKeyboardEvent(event: KeyboardEvent)
}
