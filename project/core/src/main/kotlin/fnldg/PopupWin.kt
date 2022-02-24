package fnldg

import com.badlogic.gdx.math.Rectangle
import fnldg.font.CustomChar
import fnldg.font.FontPixelSize
import fnldg.g.GBatch

class PopupWin(x: Float, y: Float) : Display {
  val rect = Rectangle(x * FirstScreen.pixelSize, y * FirstScreen.pixelSize, FirstScreen.pixelSize * 50, FirstScreen.pixelSize * 10)
  val text = CustomChar.obtain().init('C', FontPixelSize.NORMAL, rect.x, rect.y)

  override fun display(batch: GBatch) {
    text.display(batch)
  }
}