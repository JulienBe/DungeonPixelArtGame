package fnldg.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import fnldg.g.GBatch
import fnldg.ui.mode.AddTag
import fnldg.ui.mode.Mode
import fnldg.ui.mode.SelectPixel
import ktx.app.KtxInputAdapter
import ktx.collections.GdxArray

class ModeInput : KtxInputAdapter {

  private var mode: Mode = SelectPixel
  private val keys = GdxArray<Input.Keys>()
  private val touches = GdxArray<ScreenCoord>()

  fun newFrame() {
    keys.clear()
    touches.forEach { ScreenCoord.pool.free(it) }
  }

  override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    val y: Float = (Gdx.graphics.height - Gdx.input.y).toFloat()
    val x: Float = Gdx.input.x.toFloat()
    touches.add(ScreenCoord.pool.obtain().set(x, y))
    mode.clicked(x, y)
    return super.touchDown(screenX, screenY, pointer, button)
  }

  override fun keyTyped(character: Char): Boolean {
    mode.keyTyped(character)
    return super.keyTyped(character)
  }

  override fun keyUp(keycode: Int): Boolean {
    keyToState[keycode]?.let {
      updateState(it)
    }
    return super.keyUp(keycode)
  }

  private fun updateState(mode: Mode) {
    this.mode = mode
    this.mode.transition(mode)
  }

  fun display(b: GBatch) {
    mode.infoDisplay(b)
  }

  companion object {
    val keyToState = mapOf(
      Input.Keys.A  to AddTag,
      Input.Keys.F1 to AddTag,
      Input.Keys.S  to SelectPixel,
      Input.Keys.F2 to SelectPixel,
    )
  }

}

