package ee.dustland.kotlin.interpolator

import kotlin.math.*

class DecelerateInterpolator(private val factor: Double) : Interpolator {
    constructor(): this(factor = 1.0)

    override fun interpolationAt(time: Double): Double {
        return if (this.factor == 1.0) {
            1.0 - (1.0 - time) * (1.0f - time)
        } else {
            1.0 - (1.0 - time).pow(2.0 * this.factor)
        }
    }
}