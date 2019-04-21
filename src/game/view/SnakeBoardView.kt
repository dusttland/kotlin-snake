package game.view

import game.geo.Point
import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object SnakeBoardView {

    const val ID = "snake-id"

    fun boxIdOf(point: Point) = "box-${point.x}-${point.y}"

    fun nodeOfSize(size: Int) = document.create.table {
        id = ID
        for (i in 0..size) {
            tr {
                for (j in 0..size) {
                    td("snake-board-box") {
                        id = boxIdOf(Point(j, i))
                    }
                }
            }
        }
    }

}
