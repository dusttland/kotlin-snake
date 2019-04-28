package controller

import ee.dustland.kotlin.geo.Direction
import ee.dustland.kotlin.js.app.Controller
import ee.dustland.kotlin.js.keyevent.arrowKeyDirection
import ee.dustland.kotlin.js.keyevent.character
import game.Game
import game.GameListener
import org.w3c.dom.Element
import org.w3c.dom.events.KeyboardEvent
import view.MainView
import kotlin.dom.*

class MainController(
        containerID: String
) : Controller(containerID),
    GameListener {

    override fun viewNode() = MainView.node

    private lateinit var sizeText: Element
    private lateinit var snakeBoardContainer: Element

    private lateinit var game: Game

    override fun onCreate() {
        this.sizeText = this.findElement(MainView.ID.SIZE)
        this.snakeBoardContainer = this.findElement(MainView.ID.SNAKE_BOARD_CONTAINER)

        this.game = Game(this.snakeBoardContainer, size = 20, listener = this)
    }

    override fun onSnakeSizeChanged(size: Int) {
        this.sizeText.innerHTML = "$size"
    }

    override fun onKeyboardEvent(event: KeyboardEvent) {
        val arrowKeyDirection: Direction? = event.arrowKeyDirection
        when {

            arrowKeyDirection != null -> this.game.activeDirection = arrowKeyDirection

            event.character == ' ' -> {
                if (!this.game.isRunning) {
                    this.game.start()
                }
            }

        }
    }

}
