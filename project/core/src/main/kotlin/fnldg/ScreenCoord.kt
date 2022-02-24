package fnldg

import ktx.assets.pool

class ScreenCoord(var x: Float, var y: Float) {
  companion object {
    val pool = pool { ScreenCoord(0f, 0f) }
  }
}
