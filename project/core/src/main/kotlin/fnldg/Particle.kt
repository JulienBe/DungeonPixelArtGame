package fnldg

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.Rectangle
import fnldg.g.GPool
import fnldg.ui.Screen
import ktx.collections.GdxArray

class Particle private constructor() {
  private var x = 0
  private var y = 0
  private var intColor = 0
  // if it's transparent, use something else
  var color: Color = if (intColor == 0) Color.BLACK else Color(intColor)
  var xf = x.toFloat()
  var yf = y.toFloat()
  private val tags = GdxArray<Tag>()
  private val rect = Rectangle(xf * Screen.pixelSize, yf * Screen.pixelSize, Screen.pixelSize, Screen.pixelSize)

  private fun init(x: Int, y: Int, intColor: Int): Particle {
    this.x = x
    this.y = y
    xf = x.toFloat()
    yf = y.toFloat()
    this.intColor = intColor
    color = if (intColor == 0) Color.BLACK else Color(intColor)
    rect.setPosition(xf * Screen.pixelSize, yf * Screen.pixelSize)
    return this
  }

  fun contains(x: Float, y: Float) = rect.contains(x, y)

  companion object {
    private val pool = GPool { Particle() }
    fun pixmapToParticles(pixmap: Pixmap): GdxArray<Particle> {
      val particles = GdxArray<Particle>()
      for (x in 0..pixmap.width)
        for (y in 0..pixmap.height)
          particles.add(pool.obtain().init(x, (pixmap.height - 1) - y, pixmap.getPixel(x, y)))
      return particles
    }
  }
}

data class Tag(val name: String)