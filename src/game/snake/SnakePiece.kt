package game.snake

import geo.Point

class SnakePiece(
        var location: Point
) {
    constructor(x: Int, y: Int) : this(Point(x, y))

    override fun toString() = "SnakePiece(${this.location.x}, ${this.location.y})"
}
