package game.snake

import geo.Direction
import geo.Point

class Snake(
        headLocation: Point
) {

    private val pieces: MutableList<SnakePiece> = mutableListOf(
            SnakePiece(headLocation),
            SnakePiece(headLocation + Direction.DOWN.translation)
    )

    private var growCount = 0


    override fun toString() = "Length: ${this.length}, Size: ${this.size}, Pieces: ${this.pieces}"


    val pieceLocations: List<Point>
        get() = this.pieces.map { it.location }

    val length: Int
        get() = this.pieces.size

    val size: Int
        get() = this.length + this.growCount

    val movingDirection: Direction
        get() {
            val moveTranslation: Point = this.head.location - this.neck.location
            return Direction.fromTranslation(moveTranslation)
        }

    val head: SnakePiece
        get() = this.pieces[0]


    fun grow() {
        this.growCount++
    }

    fun move(direction: Direction) {
        this.throwExceptionIfInvalidDirection(direction)
        val trailLocation = movePiecesForwardAndGetTrail(direction)
        if (this.isGrowing) {
            this.growTo(trailLocation)
        }
    }

    fun isAt(point: Point): Boolean = this.pieces.any { point == it.location }

    fun isAt(direction: Direction): Boolean = this.isAt(this.head.location + direction.translation)


    private val neck: SnakePiece
        get() = this.pieces[1]

    private val isGrowing: Boolean
        get() = this.growCount > 0

    private fun addPieceTo(location: Point) {
        val newPiece = SnakePiece(location)
        this.pieces.add(newPiece)
    }

    private fun throwExceptionIfInvalidDirection(direction: Direction) {
        if (direction == this.movingDirection.opposite) {
            throw IllegalArgumentException("Can't move to snake's opposite direction.")
        }

        if (this.isAt(direction)) {
            throw IllegalArgumentException("Can't move into itself.")
        }
    }

    private fun movePiecesForwardAndGetTrail(direction: Direction): Point {
        var nextLocation = this.head.location + direction.translation
        this.pieces.forEach { piece ->
            val oldLocation = piece.location
            piece.location = nextLocation
            nextLocation = oldLocation
        }
        return nextLocation
    }

    private fun growTo(location: Point) {
        this.addPieceTo(location)
        this.growCount--
    }

}
