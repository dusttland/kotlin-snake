package snake

class Snake {

    private val pieces: MutableList<SnakePiece> = mutableListOf(
            SnakePiece(10, 10),
            SnakePiece(10, 11)
    )

    override fun toString(): String {
        return "Length: ${this.length}, Pieces: ${this.pieces}"
    }

    fun grow() {
        val lastPiece = this.pieces.lastOrNull() ?: return
        val newLocation = lastPiece.location.translateBy(x = 0, y = 1)
        val newPiece = SnakePiece(newLocation)
        this.pieces.add(newPiece)
    }

    val length: Int
        get() = this.pieces.size

}
