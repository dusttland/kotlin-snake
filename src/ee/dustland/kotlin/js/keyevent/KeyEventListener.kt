package ee.dustland.kotlin.js.keyevent

import ee.dustland.kotlin.geo.Direction

interface KeyEventListener {
    fun onArrowKey(direction: Direction)
}
