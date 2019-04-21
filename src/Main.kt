import game.Game
import game.view.PageHeaderView
import kotlin.browser.document

fun main() {
    val container = document.getElementById("container") ?: error("no container")
    container.appendChild(PageHeaderView.node)
    Game()
}
