package fnldg

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.Rectangle
import ktx.collections.GdxArray

data class Particle(var x: Int, var y: Int, val color: Int) {
  var xf = x.toFloat()
  var yf = y.toFloat()
  // yeah don't care about allocations in this project
  val rect = Rectangle(xf * FirstScreen.pixelSize, yf * FirstScreen.pixelSize, FirstScreen.pixelSize, FirstScreen.pixelSize)

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
