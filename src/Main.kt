import game.Game
import game.geo.Direction
import game.snake.*
import kotlin.browser.document
import kotlinx.html.*
import kotlinx.html.dom.create
import kotlin.browser.window

fun main() {
    println("Hello snake.")

    val snake = Snake()
    snake.grow()
    snake.grow()
    println(snake)
    snake.move(Direction.RIGHT)
    snake.move(Direction.RIGHT)
    snake.move(Direction.UP)
    snake.move(Direction.LEFT)
    println(snake)

    Game()

    window.setInterval({
        val myDiv = document.create.div("panel") {
            p {
                +"Here is "
                a("https://kotlinlang.org") { +"official Kotlin site" }
            }
            div {
                +"Hello writing stuff here."
            }
        }

        document.getElementById("container")!!.appendChild(myDiv)

//        not working for some reason
//        source: https://github.com/Kotlin/kotlinx.html
//        document.getElementById("container")!!.append {
//            div {
//                +"added it"
//            }
//        }
    }, 1000)
}
