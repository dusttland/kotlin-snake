import controller.MainController
import kotlin.browser.document

fun main() {
    val container = document.getElementById("container") ?: error("no container")
    MainController(container)
}
