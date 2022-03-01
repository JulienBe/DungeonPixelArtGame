package fnldg.ui.mode

sealed interface Mode {
  fun transition(fromMode: Mode) {}
  fun clicked(x: Float, y: Float) {}
  fun keyTyped(char: Char) {}
}