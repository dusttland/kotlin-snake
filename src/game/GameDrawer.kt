package game

import controller.findElement
import controller.removeChildren
import game.geo.Point
import game.snake.Snake
import org.w3c.dom.Element

class GameDrawer(
        private val container: Element,
        private val boardSize: Int
) {

    private val boardBoxes: Map<Point, Element>


    init {
        val boardNode = SnakeBoardView.nodeOfSize(boardSize)
        this.container.removeChildren()
        this.container.appendChild(boardNode)
        this.boardBoxes = this.boardBoxes()
    }


    fun draw(snake: Snake) {
        for (i in 0 until this.boardSize) {
            for (j in 0 until this.boardSize) {
                this.drawBoxAt(snake, Point(i, j))
            }
        }
    }


    private fun drawBoxAt(snake: Snake, point: Point) {
        val isActive = snake.isAt(point)
        val element = this.boxElementAt(point)

        element.classList.remove("active")

        if (isActive) {
            element.classList.add("active")
        }
    }

    private fun boardBoxes(): Map<Point, Element> {
        val boardBoxes = mutableMapOf<Point, Element>()
        for (i in 0 until this.boardSize) {
            for (j in 0 until this.boardSize) {
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
        val snakeBoard = this.container.findElement(SnakeBoardView.ID.BOARD)
        return snakeBoard.findElement(SnakeBoardView.ID.boxIdOf(point))
    }

}
