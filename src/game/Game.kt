package game

import game.geo.Direction
import game.geo.Point
import keyevent.KeyEvent
import keyevent.KeyEventListener
import game.snake.Snake
import org.w3c.dom.Element

class Game(
        container: Element,
        size: Int,
        var listener: GameListener?
) : KeyEventListener {

    private val params = GameParams(
            container = container,
            snake = Snake(),
            boardSize = size
    )

    private val drawer = GameDrawer(this.params)


    init {
        KeyEvent(listener = this)
        this.drawer.draw()
        this.listener?.onGameStateChanged(this.gameStats)
    }


    val gameStats: GameStats
        get() = GameStats(this.snake.size)


    override fun onArrowKey(direction: Direction) {
        this.moveSnakeTo(direction)
        this.listener?.onGameStateChanged(this.gameStats)
    }


    private val snake: Snake
        get() = this.params.snake

    private val foodLocation: Point
        get() = this.params.foodLocation

    private fun moveSnakeTo(direction: Direction) {
        this.snake.move(direction)
        if (this.snake.head.location == this.foodLocation) {
            this.snake.grow()
            this.params.randomizeFoodLocation()
        }
        this.drawer.draw()
    }

}
