package keyevent

import geo.Direction

interface KeyEventListener {
    fun onArrowKey(direction: Direction)
}
