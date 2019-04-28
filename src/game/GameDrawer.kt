package game

import game.view.SnakeBoardView
import ee.dustland.kotlin.geo.Point
import ee.dustland.kotlin.geo.PointD
import ee.dustland.kotlin.geo.RectD
import ee.dustland.kotlin.geo.pointD
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import ee.dustland.kotlin.js.utils.*
import kotlin.dom.addClass
import kotlin.dom.removeClass

class GameDrawer(
        private val params: GameParams
) {

    private val canvasElement: HTMLCanvasElement


    init {
        this.initializeViewInContainer()
        this.canvasElement = this.canvasElement()
    }


    fun draw() {
        val canvas = this.canvas()
        this.drawCanvasFrame()
        this.clearCanvas(canvas)
        this.drawSnake(canvas)
        this.drawFood(canvas)
    }


    private fun canvas(): CanvasRenderingContext2D = this.canvasElement.canvasRenderingContext2D

    private fun drawCanvasFrame() {
        if (this.params.status == GameStatus.ENDED) {
            this.canvasElement.addClass(SnakeBoardView.Class.BAD)
        } else {
            this.canvasElement.removeClass(SnakeBoardView.Class.BAD)
        }
    }

    private fun drawSnake(canvas: CanvasRenderingContext2D) {
        canvas.fillStyle = "#09c509"
        this.params.snake.pieceLocations.forEach {
            canvas.fillRect(it.canvasRect)
        }
    }

    private fun drawFood(canvas: CanvasRenderingContext2D) {
        canvas.fillStyle = "red"
        canvas.fillRect(this.params.foodLocation.canvasRect)
    }

    private fun initializeViewInContainer() {
        this.params.container.removeChildren()
        val boardNode = SnakeBoardView.nodeOfSize(this.params.boardSize)
        this.params.container.appendChild(boardNode)
    }

    private fun clearCanvas(canvas: CanvasRenderingContext2D) {
        val width: Double = this.canvasElement.width.toDouble()
        val height: Double = this.canvasElement.height.toDouble()
        canvas.clearRect(0.0, 0.0, width, height)
    }

    private fun canvasElement(): HTMLCanvasElement {
        val canvasElement = this.params.container.findElement(SnakeBoardView.ID.BOARD) as HTMLCanvasElement
        canvasElement.style.width = "${canvasElement.width / 4}px"
        canvasElement.style.height = "${canvasElement.height / 4}px"
        return canvasElement
    }

    private val Point.canvasRect: RectD
        get() {
            val boxSize = SnakeBoardView.Size.BOX_SIZE.toDouble()
            val boxPadding = SnakeBoardView.Size.BOX_PADDING.toDouble()
            val pointD = this.pointD
            val topLeft = PointD(
                    boxPadding + pointD.x * boxSize + pointD.x * boxPadding,
                    boxPadding + pointD.y * boxSize + pointD.y * boxPadding
            )
            val bottomRight = topLeft + PointD(boxSize, boxSize)
            return RectD(topLeft, bottomRight)
        }

}
