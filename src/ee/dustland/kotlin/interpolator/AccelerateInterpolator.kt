package ee.dustland.kotlin.interpolator

import kotlin.math.*

class AccelerateInterpolator(private val factor: Double) : Interpolator {
    constructor(): this(factor = 1.0)

    override fun interpolationAt(time: Double): Double {
        return if (this.factor == 1.0) {
            time * time
        } else {
            time.pow(2.0 * this.factor)
        }
    }
}