package game

import controller.findElement
import controller.removeChildren
import game.geo.Point
import org.w3c.dom.Element

class GameDrawer(
        private val params: GameParams
) {

    private val boardBoxes: Map<Point, Element>


    init {
        this.initializeViewInContainer()
        this.boardBoxes = this.boardBoxes()
    }


    fun draw() {
        for (i in 0 until this.params.boardSize) {
            for (j in 0 until this.params.boardSize) {
                this.drawBoxAt(Point(i, j))
            }
        }
    }


    private fun drawBoxAt(point: Point) {
        val isSnake = this.params.snake.isAt(point)
        val hasFood = point == this.params.foodLocation
        val element = this.boxElementAt(point)

        element.classList.remove(SNAKE_PIECE_CLASS, FOOD_CLASS)

        if (isSnake) {
            element.classList.add(SNAKE_PIECE_CLASS)
        } else if (hasFood) {
            element.classList.add(FOOD_CLASS)
        }
    }

    private fun boardBoxes(): Map<Point, Element> {
        val boardBoxes = mutableMapOf<Point, Element>()
        for (i in 0 until this.params.boardSize) {
            for (j in 0 until this.params.boardSize) {
                val point = Point(i, j)
                val box = this.findBoxElementAt(point)
                boardBoxes[point] = box
            }
        }
        return boardBoxes
    }

    private fun boxElementAt(point: Point): Element {
        return this.boardBoxes[point] ?: throw IllegalArgumentException("No board box at $point.")
    }

    private fun findBoxElementAt(point: Point): Element {
        val snakeBoard = this.params.container.findElement(SnakeBoardView.ID.BOARD)
        return snakeBoard.findElement(SnakeBoardView.ID.boxIdOf(point))
    }

    private fun initializeViewInContainer() {
        this.params.container.removeChildren()
        val boardNode = SnakeBoardView.nodeOfSize(this.params.boardSize)
        this.params.container.appendChild(boardNode)
    }


    companion object {
        const val SNAKE_PIECE_CLASS = "active"
        const val FOOD_CLASS = "food"
    }

}
