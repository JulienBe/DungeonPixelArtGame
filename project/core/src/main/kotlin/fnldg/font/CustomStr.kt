package fnldg.font

import fnldg.Display
import fnldg.g.GBatch
import fnldg.g.GPool
import fnldg.input.ScreenCoord
import ktx.collections.GdxArray

class CustomStr private constructor() : Display {
  private var x = 0f
  private var y = 0f
  private var fontSize = FontPixelSize.NORMAL
  private val chars = GdxArray<CustomChar>()

  fun update(string: String) {
    chars.forEach { CustomChar.pool.free(it) }
    chars.clear()
    string.forEachIndexed { index, c ->
      chars.add(CustomChar.pool.obtain().init(c, fontSize, x + index * fontSize.wPlusSpace, y))
    }
  }

  override fun display(batch: GBatch) {
    chars.forEach { it.display(batch, fontSize) }
  }

  fun addChar(c: Char, x: Float, y: Float) {
    chars.add(CustomChar.pool.obtain().init(c, fontSize, x + (chars.size * fontSize.wPlusSpace), y))
  }

  companion object {
    val pool = GPool { CustomStr() }
  }
}