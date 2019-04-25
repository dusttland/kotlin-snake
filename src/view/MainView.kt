package view

import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.Node
import kotlin.browser.document

object MainView {

    object ID {
        const val STATS = "snake-stats"
        const val SNAKE_BOARD_CONTAINER = "snake-board-container"
    }

    val node: Node
        get() = document.create.div {
            h1 { +"This is the Snake." }
            p { +"Here you can play snake somewhere" }
            h2 {
                id = ID.STATS

                +"Here are some stats."
            }
            div {
                id = ID.SNAKE_BOARD_CONTAINER
            }
        }

}
