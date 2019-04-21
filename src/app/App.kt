package app

import react.*
import react.dom.*
import logo.*
import ticker.*
import game.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-header") {
            logo()
            h2 {
                +"Welcome to React with Kotlin"
            }
        }
        game()
    }
}

fun RBuilder.app() = child(App::class) {}
