import snake.*

fun main() {
    println("Hello snake.")

    val snake = Snake()
    snake.grow()
    snake.grow()
    println(snake.movingDirection)
    snake.move(Direction.RIGHT)
    snake.move(Direction.RIGHT)
    snake.move(Direction.UP)
    println(snake)
}
