package snake

class Snake {

    private val pieces: MutableList<SnakePiece> = mutableListOf(
            SnakePiece(10, 10),
            SnakePiece(10, 11)
    )

    override fun toString(): String {
        return "Length: ${this.length}, Pieces: ${this.pieces}"
    }

    val length: Int
        get() = this.pieces.size

    fun grow() {
        val lastPiece = this.pieces.lastOrNull() ?: return
        val newLocation = lastPiece.location.translateBy(x = 0, y = 1)
        val newPiece = SnakePiece(newLocation)
        this.pieces.add(newPiece)
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

        var nextLocation = this.head.location.translateBy(direction.translation)
        this.pieces.forEach { piece ->
            val oldLocation = piece.location
            piece.location = nextLocation
            nextLocation = oldLocation
        }
    }

    private val head: SnakePiece
        get() = this.pieces[0]

    private val neck: SnakePiece
        get() = this.pieces[1]

}
