package fnldg.font

import fnldg.Display
import fnldg.g.GBatch
import ktx.collections.GdxArray

class CustomStr() : Display {
  var x = 0f
  var y = 0f
  var fontSize = FontPixelSize.NORMAL
  val chars = GdxArray<CustomChar>()

  fun update(string: String) {
    chars.forEach { it.free() }
    chars.clear()
    string.forEachIndexed { index, c ->
      chars.add(CustomChar.obtain().init(c, fontSize, x + index * fontSize.wPlusSpace, y))
    }
  }

  override fun display(batch: GBatch) {
    chars.forEach { it.display(batch) }
  }
}