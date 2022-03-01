package fnldg.ui.mode

import fnldg.ui.Screen

object SelectPixel : Mode {
  override fun clicked(x: Float, y: Float) {
    val clicked = Screen.imagePixel.particles.firstOrNull { it.rect.contains(x, y) }
    if (clicked != null) {
      if (Screen.imagePixel.selectedParticles.contains(clicked, true))
        Screen.imagePixel.selectedParticles.removeValue(clicked, true)
      else
        Screen.imagePixel.selectedParticles.add(clicked)
    }
  }
}