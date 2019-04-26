package game.ticker

import ee.dustland.kotlin.js.utils.clearInterval
import ee.dustland.kotlin.js.utils.interval

class Ticker(
        private val milliseconds: Int,
        private val listener: TickListener
) {

    private var interval: Int? = null


    val isRunning: Boolean
        get() = this.interval != null

    fun start() {
        this.stop()
        this.interval = interval(milliseconds = this.milliseconds) {
            this.listener.onTick()
        }
    }

    fun stop() {
        val interval = this.interval ?: return
        clearInterval(interval)
        this.interval = null
    }

}