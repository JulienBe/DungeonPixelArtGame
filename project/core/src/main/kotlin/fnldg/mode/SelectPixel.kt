package fnldg.mode

import com.badlogic.gdx.Input

object SelectPixel : Mode {
  override fun clicked(x: Float, y: Float) {
    val clicked = state.particles.firstOrNull { it.rect.contains(x, y) }
    if (clicked != null) {
      if (state.selectedParticles.contains(clicked, true))
        state.selectedParticles.removeValue(clicked, true)
      else
        state.selectedParticles.add(clicked)
    }
  }

  override fun keyDown(key: Input.Keys) {
  }
}