package game

interface GameListener {
    fun onGameStatusChanged(isRunning: Boolean)
    fun onSnakeSizeChanged(size: Int)
}
