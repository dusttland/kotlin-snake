package game

import game.geo.Point
import game.snake.Snake
import org.w3c.dom.Element

class GameParams(
        val container: Element,
        val snake: Snake,
        val boardSize: Int
) {

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

}
