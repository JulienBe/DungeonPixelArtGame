package fnldg.ui.mode

import fnldg.Display
import fnldg.Layer
import fnldg.ui.PopupWin

object AddTag : Mode {

  private var window: PopupWin = PopupWin.pool.obtain().init(15f, 15f)

  override fun transition(fromMode: Mode) {
    Display.add(window, Layer.UI)
    super.transition(fromMode)
  }

  override fun keyTyped(char: Char) {
    window.addChar(char)
    super.keyTyped(char)
  }
}