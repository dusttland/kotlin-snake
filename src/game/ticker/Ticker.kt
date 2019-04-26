package game.ticker

import utils.clearInterval
import utils.interval

class Ticker(private val listener: TickListener) {

    private var interval: Int? = null


    val isRunning: Boolean
        get() = this.interval != null

    fun start() {
        this.stop()
        this.interval = interval(milliseconds = 100) {
            this.listener.onTick()
        }
    }

    fun stop() {
        val interval = this.interval ?: return
        clearInterval(interval)
        this.interval = null
    }

}