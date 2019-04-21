import game.Game
import game.view.pageHeader
import kotlin.browser.document

fun main() {
    val container = document.getElementById("container") ?: error("no container")
    container.appendChild(pageHeader)
    Game()
}
