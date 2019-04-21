package game

import game.geo.Direction
import game.geo.Point
import game.keyevent.KeyEvent
import game.keyevent.KeyEventListener
import game.snake.Snake
import game.view.snakeBoard
import org.w3c.dom.Element
import kotlin.browser.document

class Game : KeyEventListener {

    private val snake = Snake()
    private lateinit var snakeBoard: Element

    init {
        KeyEvent(listener = this)
        this.prepareBoard()
        this.drawSnake()
        this.snake.grow()
        this.snake.grow()
        this.snake.grow()
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
        val element = this.boxElementAt(point) ?: return

        element.classList.remove("active")

        if (isActive) {
            element.classList.add("active")
        }
    }

    private fun prepareBoard() {
        val container = document.getElementById("container") ?: error("Missing #container")
        val snakeBoard = snakeBoard(BOARD_SIZE)
        container.appendChild(snakeBoard)
        this.snakeBoard = container.querySelector("#snake-board") ?: error("Missing #snake-board")
    }

    private fun boxElementAt(point: Point): Element? {
        val boardPoint = point.snakeBoardPoint
        val selector = "#box-${boardPoint.x}-${boardPoint.y}"
        return this.snakeBoard.querySelector(selector)
    }

    private val Point.snakeBoardPoint: Point
        get() = Point(this.y, this.x)

    companion object {
        const val BOARD_SIZE = 30
    }

}
