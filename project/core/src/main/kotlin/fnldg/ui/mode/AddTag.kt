package fnldg.ui.mode

import fnldg.Display
import fnldg.Layer
import fnldg.PopupWin

object AddTag : Mode {
  override fun transition(fromMode: Mode) {
    Display.add(PopupWin(15f, 15f), Layer.UI)
    super.transition(fromMode)
  }
}