package game

import ee.dustland.kotlin.color.Color
import game.view.SnakeBoardView
import ee.dustland.kotlin.geo.Point
import ee.dustland.kotlin.geo.PointD
import ee.dustland.kotlin.geo.RectD
import ee.dustland.kotlin.geo.pointD
import ee.dustland.kotlin.interpolator.AccelerateInterpolator
import ee.dustland.kotlin.interpolator.DecelerateInterpolator
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import ee.dustland.kotlin.js.utils.*
import kotlin.dom.addClass
import kotlin.dom.removeClass

class GameDrawer(
        private val params: GameParams
) {

    private val canvasElement: HTMLCanvasElement

    private var previousSnakePoints: List<Point> = listOf()
    private var previousFoodLocation: Point = this.params.foodLocation
    private var foodFadeIn: Pair<Point, Double>? = null
    private val snakeFadeOut: MutableMap<Point, Double> = mutableMapOf()

    private val snakeColor = Color(9, 179, 9)
    private val foodColor = Color(255, 0, 0)


    init {
        this.initializeViewInContainer()
        this.canvasElement = this.canvasElement()
    }


    fun draw() {
        val canvas = this.canvas()
        this.prepareAnimationData()
        this.drawCanvasFrame()
        this.clearCanvas(canvas)
        this.drawFoodAnimation(canvas)
        this.drawFood(canvas)
        this.drawSnakeAnimation(canvas)
        this.drawSnake(canvas)
    }

    val areAnimationsRunning: Boolean
        get() = !this.snakeFadeOut.isEmpty() || this.foodFadeIn != null


    private fun canvas(): CanvasRenderingContext2D = this.canvasElement.canvasRenderingContext2D

    private fun prepareAnimationData() {
        val pieces = this.params.snake.pieceLocations
        this.previousSnakePoints.subtract(pieces).forEach {
            this.snakeFadeOut[it] = millisNow()
        }

        this.previousSnakePoints = pieces

        if (this.params.foodLocation != this.previousFoodLocation) {
            this.foodFadeIn = Pair(this.params.foodLocation, millisNow())
            this.previousFoodLocation = this.params.foodLocation
        }
    }

    private fun drawCanvasFrame() {
        if (this.params.status == GameStatus.ENDED) {
            this.canvasElement.addClass(SnakeBoardView.Class.BAD)
        } else {
            this.canvasElement.removeClass(SnakeBoardView.Class.BAD)
        }
    }

    private fun drawSnake(canvas: CanvasRenderingContext2D) {
        canvas.fillStyle = this.snakeColor.hex
        this.params.snake.pieceLocations.forEach {
            canvas.fillRect(it.canvasRect)
        }
    }

    private fun drawSnakeAnimation(canvas: CanvasRenderingContext2D) {
        this.snakeFadeOut.forEach { entry ->
            val point = entry.key
            val startTime = entry.value

            val currentTime = millisNow()
            if (currentTime - startTime > OUT_DURATION) {
                this.snakeFadeOut.remove(point)
                return@forEach
            }

            val time = (OUT_DURATION - currentTime + startTime) / OUT_DURATION
            val interpolator = AccelerateInterpolator()
            val transparency = interpolator.interpolationAt(time)
            val color = this.snakeColor.withAlpha(transparency)

            canvas.fillStyle = color.hex
            canvas.fillRect(point.canvasRect)
        }
    }

    private fun drawFood(canvas: CanvasRenderingContext2D) {
        if (this.foodFadeIn == null) {
            canvas.fillStyle = this.foodColor.hex
            canvas.fillRect(this.params.foodLocation.canvasRect)
        }
    }

    private fun drawFoodAnimation(canvas: CanvasRenderingContext2D) {
        val currentTime = millisNow()
        var foodIn = this.foodFadeIn
        if (foodIn != null && currentTime - foodIn.second > FOOD_DURATION) {
            this.foodFadeIn = null
        }

        foodIn = this.foodFadeIn
        if (foodIn != null) {
            val time = (currentTime - foodIn.second) / FOOD_DURATION
            val interpolator = DecelerateInterpolator()
            val transparency = interpolator.interpolationAt(time)
            val color = this.foodColor.withAlpha(transparency)
            canvas.fillStyle = color.hex
            canvas.fillRect(foodIn.first.canvasRect)
        }
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

    companion object {
        const val OUT_DURATION = 300.0
        const val FOOD_DURATION = 500.0
    }

}
