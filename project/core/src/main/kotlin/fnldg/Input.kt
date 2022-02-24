package fnldg

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ktx.app.KtxInputAdapter

class Input(val sharedState: BooooooooSharedState, var mode: Mode = Mode.SELECT_PIXEL) : KtxInputAdapter {

  override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    val y: Float = (Gdx.graphics.height - Gdx.input.y).toFloat()
    val x: Float = Gdx.input.x.toFloat()
    mode.clicked.invoke(x, y, sharedState)
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

  companion object {
    val keyToState = mapOf(
      Input.Keys.A  to Mode.ADD_TAG,
      Input.Keys.F1 to Mode.ADD_TAG,
      Input.Keys.S  to Mode.SELECT_PIXEL,
      Input.Keys.F2 to Mode.SELECT_PIXEL,
    )
  }
}

enum class Mode(val clicked: (Float, Float, BooooooooSharedState) -> Unit) {
  SELECT_PIXEL(
    clicked = { x, y, state ->
      val clicked = state.particles.firstOrNull { it.rect.contains(x, y) }
      if (clicked != null) {
        if (state.selectedParticles.contains(clicked, true))
          state.selectedParticles.removeValue(clicked, true)
        else
          state.selectedParticles.add(clicked)
      }
    }
  ),
  ADD_TAG(
    clicked = { x, y, state ->
      Display.add(PopupWin(15f, 15f), Layer.UI)
    }
  )
}
