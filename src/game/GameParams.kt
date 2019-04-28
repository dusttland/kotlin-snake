package game

import ee.dustland.kotlin.geo.Direction
import ee.dustland.kotlin.geo.Point
import game.snake.Snake
import game.ticker.Ticker
import org.w3c.dom.Element

class GameParams(
        val container: Element,
        val boardSize: Int,
        val ticker: Ticker
) {

    private var hasBeenStarted: Boolean = false

    var snake: Snake = this.initialSnake
    var foodLocation: Point = this.randomPointThatIsNotSnake
    var activeDirection: Direction = this.snake.movingDirection


    init {
        this.randomizeFoodLocation()
    }


    val isRunning: Boolean
        get() = this.ticker.isRunning

    val status: GameStatus
        get() = when {
            !this.hasBeenStarted -> GameStatus.NOT_STARTED
            this.isRunning -> GameStatus.RUNNING
            else -> GameStatus.ENDED
        }

    fun start() {
        this.hasBeenStarted = true
        this.snake = this.initialSnake
        this.activeDirection = this.snake.movingDirection
        this.ticker.start()
    }

    fun stop() {
        this.ticker.stop()
    }

    fun randomizeFoodLocation() {
        this.foodLocation = this.randomPointThatIsNotSnake
    }


    private val randomPointThatIsNotSnake: Point
        get() {
            var point = this.randomPointOnBoard
            while (this.snake.isAt(point)) {
                point = this.randomPointOnBoard
            }
            return point
        }

    private val initialSnake: Snake
        get() = Snake(this.boardCenter)

    private val randomPointOnBoard: Point
        get() {
            val range = 0 until this.boardSize
            return Point(x = range.random(), y = range.random())
        }

    private val boardCenter: Point
        get() = Point(this.boardSize / 2, this.boardSize / 2)

}
