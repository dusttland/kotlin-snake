package game

import react.*
import react.dom.*
import kotlin.browser.document
import game.keyevent.*

interface GameProps : RProps {
    var width: Int
    var height: Int
}

interface GameState : RState {
    var lastKey: String
    var field: Array<Int>
}

class Game(props: GameProps) : RComponent<GameProps, GameState>(props), KeyEventListener {
    override fun GameState.init(props: GameProps) {
        field = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    override fun componentDidMount() {
        KeyEvent(this)
    }

    override fun onUpKey() { setState { lastKey = "up" } }
    override fun onRightKey() { setState { lastKey = "right" } }
    override fun onDownKey() { setState { lastKey = "down" } }
    override fun onLeftKey() { setState { lastKey = "left" } }

    override fun RBuilder.render() {
        div("game-grid") {
            state.field.map { square ->
                div("game-grid-square") {
                    +"${square}"
                }
            }
            +"Last key: ${state.lastKey}"
        }
    }
}

fun RBuilder.game() = child(Game::class) {}
