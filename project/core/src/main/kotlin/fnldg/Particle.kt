package fnldg

import com.badlogic.gdx.graphics.Pixmap
import ktx.collections.GdxArray

data class Particle(var x: Int, var y: Int, val color: Int) {
  var xf = x.toFloat()
  var xy = y.toFloat()

  companion object {
    fun pixmapToParticles(pixmap: Pixmap): GdxArray<Particle> {
      val particles = GdxArray<Particle>()
      for (x in 0..pixmap.width)
        for (y in 0..pixmap.height)
          particles.add(Particle(x, y, pixmap.getPixel(x, y)))
      return particles
    }
  }
}
