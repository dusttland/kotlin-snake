package controller

import game.Game
import game.GameListener
import game.GameStats
import org.w3c.dom.Element
import view.MainView
import kotlin.dom.*

class MainController(
        container: Element
) : Controller(container),
    GameListener {

    override fun view() = MainView.node

    private lateinit var statusText: Element
    private lateinit var sizeText: Element
    private lateinit var snakeBoardContainer: Element

    private lateinit var game: Game

    override fun onCreate() {
        this.statusText = this.findElement(MainView.ID.STATUS)
        this.sizeText = this.findElement(MainView.ID.SIZE)
        this.snakeBoardContainer = this.findElement(MainView.ID.SNAKE_BOARD_CONTAINER)

        this.game = Game(this.snakeBoardContainer, size = 20, listener = this)
    }

    override fun onGameStateChanged(stats: GameStats) {
        val status = if (stats.isGameRunning) {
            this.statusText.removeClass(MainView.Class.BAD)
            "OK"
        } else {
            this.statusText.addClass(MainView.Class.BAD)
            "Wasted"
        }
        this.statusText.innerHTML = status
        this.sizeText.innerHTML = "${stats.snakeSize}"
    }

}
