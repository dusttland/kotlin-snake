package game.keyevent

import kotlin.browser.document

class KeyEvent(private val listener: KeyEventListener) {
    init {
        document.onkeydown = {
            when (it.keyCode) {
                KeyCode.UP -> this.listener.onUpKey()
                KeyCode.RIGHT -> this.listener.onRightKey()
                KeyCode.DOWN -> this.listener.onDownKey()
                KeyCode.LEFT -> this.listener.onLeftKey()
            }
        }
    }
}
