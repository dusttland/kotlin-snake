package ee.dustland.kotlin.geo

data class PointD(val x: Double, val y: Double) {
    operator fun plus(other: PointD) = PointD(this.x + other.x, this.y + other.y)
    operator fun minus(other: PointD) = PointD(this.x - other.x, this.y - other.y)
}
