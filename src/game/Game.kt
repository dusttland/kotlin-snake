package game

import game.geo.Direction
import game.keyevent.KeyEvent
import game.keyevent.KeyEventListener
import game.snake.Snake

class Game : KeyEventListener {

    private val snake = Snake()

    init {
        KeyEvent(this)
    }

    override fun onUpKey() {
        this.moveSnakeTo(Direction.UP)
    }

    override fun onRightKey() {
        this.moveSnakeTo(Direction.RIGHT)
    }

    override fun onDownKey() {
        this.moveSnakeTo(Direction.DOWN)
    }

    override fun onLeftKey() {
        this.moveSnakeTo(Direction.LEFT)
    }

    private fun moveSnakeTo(direction: Direction) {
        this.snake.move(direction)
        println(snake)
    }

}
