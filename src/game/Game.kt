package game

import ee.dustland.kotlin.geo.Direction
import ee.dustland.kotlin.geo.Point
import ee.dustland.kotlin.js.keyevent.KeyEvent
import ee.dustland.kotlin.js.keyevent.KeyEventListener
import game.snake.Snake
import game.ticker.TickListener
import game.ticker.Ticker
import org.w3c.dom.Element

class Game(
        container: Element,
        size: Int,
        var listener: GameListener?
) : KeyEventListener,
    TickListener {

    private val params: GameParams = GameParams(
            container = container,
            boardSize = size,
            ticker = Ticker(milliseconds = 100, listener = this)
    )

    private val drawer: GameDrawer = GameDrawer(this.params)

    private var activeDirection: Direction = this.snake.movingDirection


    init {
        KeyEvent(listener = this)
        this.ticker.start()
        this.draw()
        this.listener?.onSnakeSizeChanged(this.snake.size)
    }


    override fun onArrowKey(direction: Direction) {
        if (!this.isRunning){
            return
        }

        if (direction == this.snake.movingDirection.opposite) {
            throw IllegalArgumentException("Can't move to snakes opposite direction")
        }

        this.activeDirection = direction
    }

    override fun onTick() {
        try {
            this.moveSnakeTo(this.activeDirection)
        } catch (e: Exception) {
            this.ticker.stop()
            this.draw()
        }
    }


    private val snake: Snake
        get() = this.params.snake

    private val foodLocation: Point
        get() = this.params.foodLocation

    private val boardSize: Int
        get() = this.params.boardSize

    private val ticker: Ticker
        get() = this.params.ticker

    private val isRunning: Boolean
        get() = this.params.isRunning

    private fun draw() {
        this.drawer.draw()
    }

    private fun moveSnakeTo(direction: Direction) {
        this.throwExceptionIfInvalidDirection(direction)
        this.snake.move(direction)
        if (this.snake.head.location == this.foodLocation) {
            this.snake.grow()
            this.params.randomizeFoodLocation()
            this.listener?.onSnakeSizeChanged(this.snake.size)
        }
        this.draw()
    }

    private fun throwExceptionIfInvalidDirection(direction: Direction) {
        val point = this.nextPoint(direction)
        if (isPointOutOfBounds(point)) {
            throw IllegalArgumentException("Direction goes out of bounds.")
        }
    }

    private fun nextPoint(direction: Direction) = this.snake.head.location + direction.translation

    private fun isPointOutOfBounds(point: Point) = isDimensionOutOfBounds(point.x) || isDimensionOutOfBounds(point.y)

    private fun isDimensionOutOfBounds(dimension: Int) = dimension < 0 || dimension >= this.boardSize

}
