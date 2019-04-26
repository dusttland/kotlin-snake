import controller.MainController
import ee.dustland.kotlin.js.app.App

class SnakeApp(containerID: String) : App(containerID) {
    override fun initialController(containerID: String) = MainController(containerID)
}
