package game

import game.geo.Point
import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object SnakeBoardView {

    object ID {
        const val BOARD = "snake-board"
        fun boxIdOf(point: Point) = "box-${point.x}-${point.y}"
    }

    fun nodeOfSize(size: Int) = document.create.table {
        id = SnakeBoardView.ID.BOARD
        for (i in 0 until size) {
            tr {
                for (j in 0 until size) {
                    td("snake-board-box") {
                        id = SnakeBoardView.ID.boxIdOf(Point(j, i))
                    }
                }
            }
        }
    }

}
