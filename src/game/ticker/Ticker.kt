package game.ticker

import kotlin.browser.window

class Ticker(private val listener: TickListener) {

    private var interval: Int? = null


    val isRunning: Boolean
        get() = this.interval != null

    fun start() {
        this.stop()
        this.interval = window.setInterval({
            this.listener.onTick()
        }, 100)
    }

    fun stop() {
        val interval = this.interval ?: return
        window.clearInterval(interval)
        this.interval = null
    }

}