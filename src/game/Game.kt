package game

import ee.dustland.kotlin.geo.Direction
import ee.dustland.kotlin.geo.Point
import ee.dustland.kotlin.js.utils.clearInterval
import ee.dustland.kotlin.js.utils.interval
import game.snake.Snake
import game.ticker.TickListener
import game.ticker.Ticker
import org.w3c.dom.Element

class Game(
        container: Element,
        size: Int,
        var listener: GameListener?
) : TickListener {

    private val params: GameParams = GameParams(
            container = container,
            boardSize = size,
            ticker = Ticker(milliseconds = 102, listener = this)
    )

    private val drawer: GameDrawer = GameDrawer(this.params)

    private var isDrawerAllowed: Boolean = false
    private var drawingInterval: Int? = null


    init {
        this.draw()
        this.listener?.onSnakeSizeChanged(this.snake.size)
    }


    val isRunning: Boolean
        get() = this.params.isRunning

    var activeDirection: Direction
        get() = this.params.activeDirection
        set(direction) {
            if (!this.isRunning){
                return
            }

            if (direction == this.snake.movingDirection.opposite) {
                throw IllegalArgumentException("Can't move to snakes opposite direction")
            }

            this.params.activeDirection = direction
        }

    fun start() {
        this.isDrawerAllowed = true
        this.drawingInterval = interval(16) {
            if (this.isDrawerAllowed) {
                this.draw()
            } else if (this.drawer.areAnimationsRunning) {
                this.draw()
            } else {
                val interval = this.drawingInterval
                if (interval != null)
                    clearInterval(interval)
            }
        }
        this.params.start()
        this.draw()
        this.listener?.onSnakeSizeChanged(this.snake.size)
    }

    fun stop() {
        this.isDrawerAllowed = false
        this.params.stop()
        this.draw()
    }


    override fun onTick() {
        try {
            this.moveSnakeTo(this.activeDirection)
        } catch (e: Exception) {
            this.stop()
        }
    }

    private var snake: Snake
        get() = this.params.snake
        set(value) { this.params.snake = value }

    private val foodLocation: Point
        get() = this.params.foodLocation

    private val boardSize: Int
        get() = this.params.boardSize

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
