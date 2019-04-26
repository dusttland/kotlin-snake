package game

import ee.dustland.kotlin.geo.Point
import game.snake.Snake
import org.w3c.dom.Element

class GameParams(
        val container: Element,
        val boardSize: Int
) {

    var snake: Snake = Snake(this.boardCenter)
    var foodLocation: Point = this.randomPointThatIsNotSnake


    init {
        this.randomizeFoodLocation()
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

    private val randomPointOnBoard: Point
        get() {
            val range = 0 until this.boardSize
            return Point(x = range.random(), y = range.random())
        }

    private val boardCenter: Point
        get() = Point(this.boardSize / 2, this.boardSize / 2)

}
