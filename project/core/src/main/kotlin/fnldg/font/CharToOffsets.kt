package fnldg.font

import com.badlogic.gdx.Gdx

/**
 * The goal is to map a char to the positions of the pixels that will form this char
 */
object CharToOffsets {

  private val offsets = Array(15) { Offset(it) }

  val mapping: Map<Char, List<Offset>> = Gdx.files.internal("fonts")
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
          .map { offsets[it] }
          .reversed()
      })

  fun randomChar(): Char {
    return mapping.keys.random()
  }
}

class Offset(index: Int) {
  val x = index % 3
  val y = 4 - (index / 3)
}