package game.view

import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object SnakeBoardView {

    object ID {
        const val BOARD = "snake-board"
    }

    object CLASS {
        const val BOARD = "snake-board"
    }

    object SIZE {
        const val BOX_SIZE = 42
        const val BOX_PADDING = 12
    }


    fun nodeOfSize(size: Int) = document.create.canvas {
        classes = setOf(CLASS.BOARD)
        width = sizeInPixels(size).toString()
        height = sizeInPixels(size).toString()
        id = ID.BOARD
    }

    private fun sizeInPixels(size: Int): Int {
        return size * SIZE.BOX_SIZE + (size + 1) * SIZE.BOX_PADDING
    }

}
