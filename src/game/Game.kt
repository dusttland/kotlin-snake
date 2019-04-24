package game

import game.geo.Direction
import game.keyevent.KeyEvent
import game.keyevent.KeyEventListener
import game.snake.Snake
import org.w3c.dom.Element

class Game(
        private val snakeBoard: Element,
        var listener: GameListener?
) : KeyEventListener {

    private val snake = Snake()
    private val drawer = GameDrawer(this.snakeBoard)


    init {
        KeyEvent(listener = this)
        for (i in 0..20) {
            this.snake.grow()
        }
        this.drawer.draw(this.snake)
        this.listener?.onGameStateChanged(this.gameStats)
    }


    val gameStats: GameStats
        get() = GameStats(this.snake.size)


    override fun onArrowKey(direction: Direction) {
        this.moveSnakeTo(direction)
        this.listener?.onGameStateChanged(this.gameStats)
    }

    private fun moveSnakeTo(direction: Direction) {
        this.snake.move(direction)
        this.drawer.draw(this.snake)
    }

}
