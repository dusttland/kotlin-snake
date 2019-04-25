package game.view

import geo.Point
import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object SnakeBoardView {

    object ID {
        const val BOARD = "snake-board"
        fun boxIdOf(point: Point) = "box-${point.x}-${point.y}"
    }

    object CLASS {
        const val BOARD = "snake-board"
        const val BOARD_BOX = "snake-board-box"
        const val SNAKE = "snake"
        const val FOOD = "food"
    }

    fun nodeOfSize(size: Int) = document.create.table {
        classes = setOf(CLASS.BOARD)
        id = SnakeBoardView.ID.BOARD
        for (i in 0 until size) {
            tr {
                for (j in 0 until size) {
                    td(CLASS.BOARD_BOX) {
                        id = SnakeBoardView.ID.boxIdOf(Point(j, i))
                    }
                }
            }
        }
    }

}
