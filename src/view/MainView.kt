package view

import controller.emptyDiv
import org.w3c.dom.Node

object MainView {

    val node: Node
        get()  {
            val div = emptyDiv()
            div.appendChild(PageHeaderView.node)
            div.appendChild(SnakeBoardView.nodeOfSize(30))
            return div
        }

}
