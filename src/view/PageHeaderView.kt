package view

import kotlinx.html.dom.create
import kotlinx.html.*
import kotlin.browser.document


object PageHeaderView {

    const val STATS_ID = "stats"

    val node get() = document.create.div {
        h1 { +"This is the Snake." }
        p { +"Here you can play snake somewhere" }
        h2 {
            id = STATS_ID

            +"Here are some stats."
        }
    }

}
