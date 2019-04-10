package snake

class Snake {

    private val pieces: MutableList<SnakePiece> = mutableListOf(
            SnakePiece(10, 10),
            SnakePiece(10, 11)
    )

    private var growCount = 0

    override fun toString(): String {
        return "Length: ${this.length}, Size: ${this.size}, Pieces: ${this.pieces}"
    }

    val length: Int
        get() = this.pieces.size

    val size: Int
        get() = this.length + this.growCount

    fun grow() {
        this.growCount++
    }

    val movingDirection: Direction
        get() {
            val moveTranslation: Point = this.head.location.translateBy(this.neck.location.negative)
            return Direction.fromTranslation(moveTranslation)
        }

    fun move(direction: Direction) {
        if (direction == this.movingDirection.opposite) {
            throw IllegalArgumentException("Can't move to snake's opposite direction.")
        }
        val trailLocation = movePiecesForwardAndGetTrail(direction)
        if (this.isGrowing) {
            this.growTo(trailLocation)
        }
    }

    private val head: SnakePiece
        get() = this.pieces[0]

    private val neck: SnakePiece
        get() = this.pieces[1]

    private val isGrowing: Boolean
        get() = this.growCount > 0

    private fun addPieceTo(location: Point) {
        val newPiece = SnakePiece(location)
        this.pieces.add(newPiece)
    }

    private fun movePiecesForwardAndGetTrail(direction: Direction): Point {
        var nextLocation = this.head.location.translateBy(direction.translation)
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
