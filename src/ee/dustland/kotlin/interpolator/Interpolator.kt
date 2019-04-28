package ee.dustland.kotlin.interpolator

interface Interpolator {
    fun interpolationAt(time: Double): Double
}
