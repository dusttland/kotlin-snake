package game

import controller.findElement
import controller.removeChildren
import game.view.SnakeBoardView
import geo.Point
import geo.PointD
import geo.RectD
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class GameDrawer(
        private val params: GameParams
) {

    private val canvasElement: HTMLCanvasElement


    init {
        this.initializeViewInContainer()
        this.canvasElement = this.canvasElement()
    }


    fun draw() {
        val canvas = this.canvasElement.getContext("2d") as CanvasRenderingContext2D
        this.clearCanvas(canvas)
        this.drawSnake(canvas)
        this.drawFood(canvas)
    }


    private fun drawSnake(canvas: CanvasRenderingContext2D) {
        val pieces = this.params.snake.pieceLocations
        canvas.fillStyle = "black"
        pieces.forEach {
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

    private fun CanvasRenderingContext2D.fillRect(rect: RectD) {
        this.fillRect(rect.minX, rect.minY, rect.width, rect.height)
    }

    private val Point.canvasRect: RectD
        get() {
            val boxSize = SnakeBoardView.SIZE.BOX_SIZE.toDouble()
            val boxPadding = SnakeBoardView.SIZE.BOX_PADDING.toDouble()
            val pointD = this.pointD
            val topLeft = PointD(
                    boxPadding + pointD.x * boxSize + pointD.x * boxPadding,
                    boxPadding + pointD.y * boxSize + pointD.y * boxPadding
            )
            val bottomRight = topLeft + PointD(boxSize, boxSize)
            return RectD(topLeft, bottomRight)
        }

}
