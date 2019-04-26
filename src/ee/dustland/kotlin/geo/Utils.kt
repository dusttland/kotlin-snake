package ee.dustland.kotlin.geo

val Point.pointD: PointD
    get() = PointD(this.x.toDouble(), this.y.toDouble())
