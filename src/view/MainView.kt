package view

import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.Node
import kotlin.browser.document

object MainView {

    object ID {
        const val SNAKE_BOARD_CONTAINER = "snake-board-container"
        const val SIZE = "snake-size"
    }

    object Class {
        const val LIGHTER = "lighter"
        const val STATS = "stats"
        const val CONTAINER = "main-view-container"
    }

    val node: Node
        get() = document.create.div(Class.CONTAINER) {
            h1 { +"Kotlin-Snake." }
            div(Class.STATS) {
                div {
                    +"Size: "
                    span(Class.LIGHTER) {
                        id = ID.SIZE
                    }
                }
            }
            div {
                id = ID.SNAKE_BOARD_CONTAINER
            }
            p {
                a("https://github.com/dusttland/kotlin-snake/tree/kotlinx") {
                    target = "blank"
                    +"Source code"
                }
            }

        }

}
