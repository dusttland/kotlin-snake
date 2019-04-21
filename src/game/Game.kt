package game

import game.geo.Direction
import game.keyevent.KeyEvent
import game.keyevent.KeyEventListener
import game.snake.Snake

class Game : KeyEventListener {

    private val snake = Snake()

    init {
        KeyEvent(listener = this)
    }

    override fun onArrowKey(direction: Direction) {
        moveSnakeTo(direction)
    }

    private fun moveSnakeTo(direction: Direction) {
        this.snake.move(direction)
        println(this.snake)
    }

}
