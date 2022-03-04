package fnldg.font

import fnldg.g.GBatch
import fnldg.g.GPool
import fnldg.g.GRnd
import ktx.collections.GdxArray

class CustomChar private constructor() {
  private var char = ' '
  private var pixels = GdxArray<FontPixel>()
  private var x = 0f
  private var y = 0f

  fun init(char: Char, size: FontPixelSize, x: Float, y: Float): CustomChar {
    this.char = char
    this.x = x
    this.y = y
    pixels.forEach { FontPixel.pool.free(it) }
    pixels.clear()

    CharToOffsets.mapping[char]?.forEach { offset ->
      val fp = FontPixel.pool.obtain()
      fp.anchorX = offset.x * size.wF
      fp.anchorY = offset.y * size.wF
      fp.x = fp.anchorX
      fp.y = fp.anchorY
      if (GRnd.nextBoolean())
        fp.x = GRnd.gauss(6f * size.dotW)
      else
        fp.y = GRnd.gauss(6f * size.dotW)
      pixels.add(fp)
    }
    return this
  }

  fun swap(newChar: Char, size: FontPixelSize) {
    this.char = newChar
    // remove if new char has less pixels
    for (i in 0 until pixels.size - CharToOffsets.mapping[newChar]!!.size) {
      val p = pixels.random()
      FontPixel.pool.free(p)
      pixels.removeValue(p, true)
    }

    // add if new char has more pixels
    for (i in 0 until CharToOffsets.mapping[newChar]!!.size - pixels.size) {
      val fp = FontPixel.pool.obtain()
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

  fun display(batch: GBatch, size: FontPixelSize) {
    pixels.forEach { it.display(batch, x, y, size) }
  }

  companion object {
    val pool = GPool { CustomChar() }
  }
}