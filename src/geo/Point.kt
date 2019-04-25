package geo

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    operator fun minus(other: Point) = Point(this.x - other.x, this.y - other.y)

    val pointD: PointD
        get() = PointD(this.x.toDouble(), this.y.toDouble())
}
