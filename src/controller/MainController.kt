package controller

import game.Game
import game.GameListener
import game.GameStats
import org.w3c.dom.Element
import view.MainView

class MainController(
        container: Element
) : Controller(container),
    GameListener {

    override fun view() = MainView.node

    private lateinit var statsText: Element
    private lateinit var snakeBoardContainer: Element

    private lateinit var game: Game

    override fun onCreate() {
        this.statsText = this.findElement(MainView.ID.STATS)
        this.snakeBoardContainer = this.findElement(MainView.ID.SNAKE_BOARD_CONTAINER)

        this.game = Game(this.snakeBoardContainer, size = 20, listener = this)
    }

    override fun onGameStateChanged(stats: GameStats) {
        if (stats.isGameRunning) {
            this.statsText.innerHTML = "Size: ${stats.snakeSize}"
        } else {
            this.statsText.innerHTML = "Game ended! Size: ${stats.snakeSize}"
        }
    }

}
