package game.keyevent

import game.geo.Direction

interface KeyEventListener {
    fun onArrowKey(direction: Direction)
}
