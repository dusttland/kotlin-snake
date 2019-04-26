package utils

import kotlin.browser.window

fun interval(milliseconds: Int, handler: () -> Unit): Int {
    return window.setInterval({
        handler.invoke()
    }, milliseconds)
}

fun clearInterval(interval: Int) {
    window.clearInterval(interval)
}

fun timeout(milliseconds: Int, handler: () -> Unit) {
    window.setTimeout({
        handler.invoke()
    }, milliseconds)
}
