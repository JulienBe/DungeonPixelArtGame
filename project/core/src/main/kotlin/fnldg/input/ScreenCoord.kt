package fnldg.input

import fnldg.g.GPool

data class ScreenCoord(var x: Float, var y: Float) {
  fun set(x: Float, y: Float): ScreenCoord {
    this.x = x
    this.y = y
    return this
  }

  companion object {
    val pool = GPool { ScreenCoord(0f, 0f) }
  }
}
