package ee.dustland.kotlin.interpolator

import kotlin.math.*

class AccelerateDecelerateInterpolator : Interpolator {
    override fun interpolationAt(time: Double) = (cos((time + 1.0) * PI) / 2.0) + 0.5
}
