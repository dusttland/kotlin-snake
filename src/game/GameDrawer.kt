package game

import controller.findElement
import game.geo.Point
import game.snake.Snake
import org.w3c.dom.Element
import view.SnakeBoardView

class GameDrawer(private val snakeBoard: Element) {

    private val boardSize: Int = this.boardSize()
    private val boardBoxes: Map<Point, Element> = this.boardBoxes()


    fun draw(snake: Snake) {
        for (i in 0 until this.boardSize) {
            for (j in 0 until this.boardSize) {
                this.drawBoxAt(snake, Point(i, j))
            }
        }
    }


    private fun boardSize() = this.snakeBoard.getElementsByTagName("tr").length

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
        return this.snakeBoard.findElement(SnakeBoardView.boxIdOf(point))
    }

}
