package game

interface GameListener {
    fun onGameStateChanged(stats: GameStats)
}
