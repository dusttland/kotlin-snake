import game.Game
import game.geo.Direction
import game.snake.*

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
}
