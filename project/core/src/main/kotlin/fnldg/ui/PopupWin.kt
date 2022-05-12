package fnldg.ui

import com.badlogic.gdx.math.Rectangle
import fnldg.Display
import fnldg.font.CustomStr
import fnldg.g.GBatch
import fnldg.g.GPool

class PopupWin private constructor(var x: Float, var y: Float) : Display {
  private val rect = Rectangle(x * Screen.pixelSize, y * Screen.pixelSize, Screen.pixelSize * 50, Screen.pixelSize * 10)
  private val text = CustomStr.obtain()

  override fun display(batch: GBatch) {
    text.display(batch)
  }

  fun init(x: Float, y: Float): PopupWin {
    this.x = x
    this.y = y
    rect.setPosition(x, y)
    return this
  }

  fun addChar(c: Char) {
    text.addChar(c, x, y)
  }

  companion object {
    val pool = GPool { PopupWin(0f, 0f) }
  }
}