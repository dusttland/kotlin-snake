package geo

data class RectD(
        val minX: Double = 0.0,
        val minY: Double = 0.0,
        val width: Double = 0.0,
        val height: Double = 0.0
) {

    constructor(topLeft: PointD, bottomRight: PointD): this(
            topLeft.x,
            topLeft.y,
            bottomRight.x - topLeft.x,
            bottomRight.y - topLeft.y
    )

    val maxX: Double
        get() = this.minX + width

    val maxY: Double
        get() = this.minY + height

}
