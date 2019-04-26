package game.view

import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object SnakeBoardView {

    object ID {
        const val BOARD = "snake-board"
    }

    object Class {
        const val BOARD = "snake-board"
    }

    object Size {
        const val BOX_SIZE = 44
        const val BOX_PADDING = 16
    }


    fun nodeOfSize(size: Int) = document.create.canvas {
        classes = setOf(Class.BOARD)
        width = sizeInPixels(size).toString()
        height = sizeInPixels(size).toString()
        id = ID.BOARD
    }

    private fun sizeInPixels(size: Int): Int {
        return size * Size.BOX_SIZE + (size + 1) * Size.BOX_PADDING
    }

}
