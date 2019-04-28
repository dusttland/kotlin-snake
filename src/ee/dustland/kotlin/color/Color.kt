package ee.dustland.kotlin.color

@ExperimentalUnsignedTypes
data class Color(
        val red: UByte,
        val green: UByte,
        val blue: UByte,
        val alpha: UByte
) {

    constructor(r: UByte, g: UByte, b: UByte):
            this(r, g, b, 0xFF.toUByte())

    constructor(r: Int, g: Int, b: Int, a: Int):
            this(r.byte, g.byte, b.byte, a.byte)

    constructor(r: Int, g: Int, b: Int):
            this(r, g, b, 255)

    constructor(r: Double, g: Double, b: Double, a: Double):
            this(r.asByteColor, g.asByteColor, b.asByteColor, a.asByteColor)

    constructor(r: Double, g: Double, b: Double):
            this(r, g, b, 1.0)

    constructor(r: Int, g: Int, b: Int, a: Double):
            this(r.byte, g.byte, b.byte, a.asByteColor)

    val r: UByte get() = this.red
    val g: UByte get() = this.green
    val b: UByte get() = this.blue
    val a: UByte get() = this.alpha

    val ri: Int get() = this.red.toInt()
    val gi: Int get() = this.green.toInt()
    val bi: Int get() = this.blue.toInt()
    val ai: Int get() = this.alpha.toInt()

    val rd: Double get() = this.red.asDoubleColor
    val gd: Double get() = this.green.asDoubleColor
    val bd: Double get() = this.blue.asDoubleColor
    val ad: Double get() = this.alpha.asDoubleColor

    val hex: String
        get() = "#${this.rHex}${this.gHex}${this.bHex}${this.aHex}"

    fun withAlpha(alpha: Double) = Color(this.r, this.g, this.b, alpha.asByteColor)

    private val rHex: String get() = this.r.stringWithPadding
    private val gHex: String get() = this.g.stringWithPadding
    private val bHex: String get() = this.b.stringWithPadding
    private val aHex: String get() = this.a.stringWithPadding

    companion object {
        fun fromHex(hex: String): Color {
            if (!isHexValid(hex)) {
                throw IllegalArgumentException("Cannot parse hex.")
            }

            val r = hex.substring(1, 3).toUByte(16)
            val g = hex.substring(3, 5).toUByte(16)
            val b = hex.substring(5, 7).toUByte(16)
            val a = if (hex.length == 9) {
                hex.substring(7, 9).toUByte(16)
            } else {
                0xFF.toUByte()
            }

            return Color(r, g, b, a)
        }

        private fun isHexValid(hex: String): Boolean {
            if (hex.isEmpty()) {
                return false
            }

            if (hex[0] != '#') {
                return false
            }

            if (hex.length != 7 && hex.length != 9) {
                return false
            }

            try {
                hex.substring(1).toULong(16)
            } catch (e: Exception) {
                return false
            }

            return true
        }
    }

}

private val UByte.stringWithPadding: String
    get() = this.toString(16).padStart(2, '0')

private val Int.byte: UByte
    get() = this.toUByte()

private val Double.asByteColor: UByte
    get() = (255.0 * this).toInt().toUByte()

private val UByte.asDoubleColor: Double
    get() = this.toInt().toDouble() / 255.0