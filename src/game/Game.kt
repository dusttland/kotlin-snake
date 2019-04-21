package game

import game.geo.Direction
import game.geo.Point
import game.keyevent.KeyEvent
import game.keyevent.KeyEventListener
import game.snake.Snake
import game.view.SnakeBoardView
import org.w3c.dom.Element
import kotlin.browser.document

class Game : KeyEventListener {

    private val snake = Snake()
    private lateinit var snakeBoard: Element
    private lateinit var boardBoxes: Map<Point, Element>

    init {
        KeyEvent(listener = this)
        this.prepareBoard()
        this.drawSnake()
        for (i in 0..20) {
            this.snake.grow()
        }
    }

    override fun onArrowKey(direction: Direction) {
        moveSnakeTo(direction)
    }

    private fun moveSnakeTo(direction: Direction) {
        this.snake.move(direction)
        this.drawSnake()
    }

    private fun drawSnake() {
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                this.drawBoxAt(Point(i, j))
            }
        }
    }

    private fun drawBoxAt(point: Point) {
        val isActive = this.snake.isAt(point)
        val element = this.boardBoxes[point] ?: throw IllegalArgumentException("No board box at $point.")

        element.classList.remove("active")

        if (isActive) {
            element.classList.add("active")
        }
    }

    private fun prepareBoard() {
        val container = document.getElementById("container")
                ?: throw IllegalStateException("Missing #container")
        val snakeBoard = SnakeBoardView.nodeOfSize(BOARD_SIZE)
        container.appendChild(snakeBoard)

        val snakeBoardSelector = "#${SnakeBoardView.ID}"
        this.snakeBoard = container.querySelector(snakeBoardSelector)
                ?: throw IllegalStateException("Missing $snakeBoardSelector")

        this.prepareBoardBoxes()
    }

    private fun prepareBoardBoxes() {
        val boardBoxes = mutableMapOf<Point, Element>()
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                val point = Point(i, j)
                val box = this.boxElementAt(point) ?: throw IllegalStateException("Missing box element at $point")
                boardBoxes[point] = box
            }
        }
        this.boardBoxes = boardBoxes
    }

    private fun boxElementAt(point: Point): Element? {
        val selector = "#${SnakeBoardView.boxIdOf(point)}"
        return this.snakeBoard.querySelector(selector)
    }

    companion object {
        const val BOARD_SIZE = 30
    }

}
