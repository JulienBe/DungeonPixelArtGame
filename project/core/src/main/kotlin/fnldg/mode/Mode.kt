package fnldg.mode

import com.badlogic.gdx.Input

sealed interface Mode {
  fun clicked(x: Float, y: Float)
  fun keyDown(key: Input.Keys)
}