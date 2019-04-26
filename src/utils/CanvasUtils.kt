package utils

import geo.RectD
import org.w3c.dom.CanvasRenderingContext2D

fun CanvasRenderingContext2D.fillRect(rect: RectD) {
    this.fillRect(rect.minX, rect.minY, rect.width, rect.height)
}
