package snake

enum class Direction {

    UP, RIGHT, DOWN, LEFT;

    val opposite: Direction
        get() = when (this) {
            UP -> DOWN
            RIGHT -> LEFT
            DOWN -> UP
            LEFT -> RIGHT
        }

    val translation: Point
        get() = when (this) {
            UP -> Point(0, -1)
            RIGHT -> Point(1, 0)
            DOWN -> Point(0, 1)
            LEFT -> Point(-1, 0)
        }

    companion object {
        fun fromTranslation(translation: Point): Direction = when (translation) {
            UP.translation -> UP
            RIGHT.translation -> RIGHT
            DOWN.translation -> DOWN
            LEFT.translation -> LEFT
            else -> throw IllegalArgumentException("Can't get direction (translation: $translation).")
        }
    }

}
