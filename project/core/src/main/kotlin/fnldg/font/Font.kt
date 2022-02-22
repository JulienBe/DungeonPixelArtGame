package fnldg.font

import com.badlogic.gdx.Gdx
import fnldg.Char

object PicoFont {

  const val w = 3f
  const val h = 5f
  const val wI = w.toInt()
  val normalFontSize = FontSize(2f)
  val bigFontSize = FontSize(3f)
  val smallSize = FontSize(1.4f)
  val picoSize = FontSize(0.7f)

  private val offsets: Map<Char, List<Offsets>> = Gdx.files.internal("fonts")
    .readString()
    .replace("SPACE", " ")
    .split("---")
    .map { it.filter { char -> char.category != CharCategory.CONTROL } }
    // so far it looks like : $111110011111010, %101001010100101, *101010111010101, +000010111010000, ...
    .associateBy(
      { it[0] },
      { charDef ->
        charDef.subSequence(1, charDef.length)
          .mapIndexed { index, c -> if (c == '1') index else -1 }
          .filter { it != -1 }
          .map { Offsets.values()[it] }
          .reversed()
      })

  private fun pos(rowcol: Int, size: FontSize) = rowcol * size.width

  fun init(char: Char, pixels: Array<FontPixel>, elemIndex: Int, fontSize: FontSize) {
    pixels.forEach { it.free() }
    pixels.clear()

    offsets[char]?.forEach {
      val fp = FontPixel.obtain(elemIndex)
      fp.anchorX = pos(it.x, fontSize)
      fp.anchorY = pos(it.y, fontSize)
      fp.x = fp.anchorX
      fp.y = fp.anchorY
      if (GRand.nextBoolean())
        fp.x = GRand.gauss(6f * fontSize.width)
      else
        fp.y = GRand.gauss(6f * fontSize.width)
      pixels.add(fp)
    }
  }

  fun swap(newChar: Char, pixels: Array<FontPixel>, elemIndex: Int, fontSize: FontSize) {
    for (i in 0 until pixels.size - offsets[newChar]!!.size) {
      val p = pixels.random()
      p.free()
      pixels.removeValue(p, true)
    }

    for (i in 0 until offsets[newChar]!!.size - pixels.size) {
      val fp = FontPixel.obtain(elemIndex)
      if (!pixels.isEmpty) {
        val ref = pixels.random()
        fp.x = ref.x
        fp.y = ref.y
      } else {
        fp.x = GRand.absGauss(2f)
        fp.y = GRand.absGauss(2f)
      }
      pixels.add(fp)
    }

    var cpt = 0
    offsets[newChar]?.forEach {
      val fp = pixels.get(cpt)
      fp.anchorX = pos(it.x, fontSize)
      fp.anchorY = pos(it.y, fontSize)
      cpt++
    }
  }

  fun randomChar(): Char {
    return offsets.keys.random()
  }

}

enum class Offsets {
  ZERO,
  ONE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
  ELVEN,
  TWELVE,
  THIRTEEN,
  FOURTEEN,
  FIFTEEN;

  val x = ordinal % 3
  val y = 4 - (ordinal / 3)
}