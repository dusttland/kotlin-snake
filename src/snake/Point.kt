package snake

data class Point(
        val x: Int,
        val y: Int
) {
    fun translateBy(x: Int, y: Int): Point {
        return Point(this.x + x, this.y + y)
    }

    fun translateBy(other: Point): Point {
        return this.translateBy(other.x, other.y)
    }
}
