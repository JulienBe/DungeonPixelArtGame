package fnldg

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import fnldg.mode.Mode
import fnldg.mode.SelectPixel
import ktx.app.KtxInputAdapter
import ktx.collections.GdxArray

object Input : KtxInputAdapter {

  private var mode: Mode = SelectPixel
  val KeyPressed = GdxArray<Input.Keys>()
  val touch = GdxArray<ScreenCoord>()
  val keyToState = mapOf(
    Input.Keys.S  to SelectPixel,
    Input.Keys.F2 to SelectPixel,
  )

  fun newFrame() {
    KeyPressed.clear()
    touch.forEach { ScreenCoord.pool.free(it) }
  }

  override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    val y: Float = (Gdx.graphics.height - Gdx.input.y).toFloat()
    val x: Float = Gdx.input.x.toFloat()
    return super.touchDown(screenX, screenY, pointer, button)
  }

  override fun keyUp(keycode: Int): Boolean {
    keyToState[keycode]?.let {
      updateState(it)
    }
    return super.keyUp(keycode)
  }

  private fun updateState(mode: Mode) {
    this.mode = mode
  }

}