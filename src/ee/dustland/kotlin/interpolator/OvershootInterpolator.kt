package ee.dustland.kotlin.interpolator

class OvershootInterpolator(private val tension: Double) : Interpolator {
    constructor(): this(tension = 2.0)

    override fun interpolationAt(time: Double): Double {
        val t = time - 1.0
        return t * t * ((this.tension + 1.0) * t + this.tension) + 1.0
    }
}