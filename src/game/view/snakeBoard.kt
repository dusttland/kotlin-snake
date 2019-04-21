package game.view

import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document

fun snakeBoard(size: Int) = document.create.table {
    id="snake-board"
    for (i in 0..size) {
        tr {
            for (j in 0..size) {
                td("snake-board-box") {
                    id = "box-$i-$j"
                }
            }
        }
    }
}
