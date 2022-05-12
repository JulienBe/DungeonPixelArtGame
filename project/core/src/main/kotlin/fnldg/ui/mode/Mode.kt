package fnldg.ui.mode

import com.badlogic.gdx.Gdx
import fnldg.font.CustomStr
import fnldg.font.FontPixelSize
import fnldg.g.GBatch

sealed class Mode(name: String) {

  private val nameDisplay: CustomStr = CustomStr.init(name, 1f, (Gdx.graphics.height - FontPixelSize.NORMAL.height).toFloat())
  fun infoDisplay(batch: GBatch) {
    nameDisplay.display(batch)
  }
  open fun transition(fromMode: Mode) {}
  open fun clicked(x: Float, y: Float) {}
  open fun keyTyped(char: Char) {}
}