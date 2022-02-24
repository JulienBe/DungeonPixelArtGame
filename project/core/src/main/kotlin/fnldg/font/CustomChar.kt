package fnldg.font

import fnldg.Display
import fnldg.g.GBatch
import fnldg.g.GRnd
import ktx.assets.pool
import ktx.collections.GdxArray

class CustomChar : Display {
  private var char = ' '
  private var pixels = GdxArray<FontPixel>()
  private var x = 0f
  private var y = 0f
  private var size = FontPixelSize.HYPER

  fun init(char: Char, size: FontPixelSize, x: Float, y: Float): CustomChar {
    this.char = char
    this.size = size
    this.x = x
    this.y = y
    pixels.forEach { it.free() }
    pixels.clear()

    CharToOffsets.mapping[char]?.forEach { offset ->
      val fp = FontPixel.obtain()
      fp.anchorX = offset.x * size.wF
      fp.anchorY = offset.y * size.wF
      fp.x = fp.anchorX
      fp.y = fp.anchorY
      if (GRnd.nextBoolean())
        fp.x = GRnd.gauss(6f * size.w)
      else
        fp.y = GRnd.gauss(6f * size.w)
      pixels.add(fp)
    }
    return this
  }

  fun swap(newChar: Char, size: FontPixelSize) {
    this.char = newChar
    // remove if new char has less pixels
    for (i in 0 until pixels.size - CharToOffsets.mapping[newChar]!!.size) {
      val p = pixels.random()
      p.free()
      pixels.removeValue(p, true)
    }

    // add if new char has more pixels
    for (i in 0 until CharToOffsets.mapping[newChar]!!.size - pixels.size) {
      val fp = FontPixel.obtain()
      if (!pixels.isEmpty) {
        val ref = pixels.random()
        fp.x = ref.x
        fp.y = ref.y
      } else {
        fp.x = GRnd.absGauss(2f)
        fp.y = GRnd.absGauss(2f)
      }
      pixels.add(fp)
    }

    // give them their new anchors
    var cpt = 0
    CharToOffsets.mapping[newChar]?.forEach { offset ->
      val fp = pixels.get(cpt)
      fp.anchorX = offset.x * size.wF
      fp.anchorY = offset.y * size.wF
      cpt++
    }
  }

  override fun display(batch: GBatch) {
    pixels.forEach { it.display(batch, x, y, size) }
  }

  companion object {
    private val pool = pool { CustomChar() }
    fun obtain(): CustomChar = pool.obtain()
  }
}