package fnldg

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.Rectangle
import ktx.collections.GdxArray

data class Particle(var x: Int, var y: Int, val originalColor: Int) {
  // if it's transparent, use something else
  val color: Color = if (originalColor == 0) Color.BLACK else Color(originalColor)
  var xf = x.toFloat()
  var yf = y.toFloat()
  // yeah don't care about allocations in this project
  val rect = Rectangle(xf * FirstScreen.pixelSize, yf * FirstScreen.pixelSize, FirstScreen.pixelSize, FirstScreen.pixelSize)
  val tags = GdxArray<Tag>()

  companion object {
    fun pixmapToParticles(pixmap: Pixmap): GdxArray<Particle> {
      val particles = GdxArray<Particle>()
      for (x in 0..pixmap.width)
        for (y in 0..pixmap.height)
          particles.add(Particle(x, (pixmap.height - 1) - y, pixmap.getPixel(x, y)))
      return particles
    }
  }
}

data class Tag(val name: String)