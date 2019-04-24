package controller

import game.Game
import game.GameListener
import game.GameStats
import org.w3c.dom.Element
import view.MainView
import view.PageHeaderView
import view.SnakeBoardView

class MainController(
        container: Element
) : Controller(container),
    GameListener {

    override fun view() = MainView.node

    private lateinit var statsText: Element
    private lateinit var snakeBoard: Element

    private lateinit var game: Game

    override fun onCreate() {
        this.statsText = findElement(PageHeaderView.STATS_ID)
        this.snakeBoard = findElement(SnakeBoardView.ID)

        this.game = Game(this.snakeBoard, listener = this)
    }

    override fun onGameStateChanged(stats: GameStats) {
        this.statsText.innerHTML = "Size: ${stats.snakeSize}"
    }

}
