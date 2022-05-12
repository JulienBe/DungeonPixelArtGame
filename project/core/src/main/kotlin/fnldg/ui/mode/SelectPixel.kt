package fnldg.ui.mode

import fnldg.ui.Screen

object SelectPixel : Mode("Select") {

  override fun clicked(x: Float, y: Float) {
    val clicked = Screen.imagePixel.particles.firstOrNull { it.contains(x, y) }
    if (clicked != null) {
      if (Screen.imagePixel.selectedParticles.contains(clicked, true))
        Screen.imagePixel.selectedParticles.removeValue(clicked, true)
      else
        Screen.imagePixel.selectedParticles.add(clicked)
    }
  }
}