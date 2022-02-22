package fnldg.font

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Pool
import fnldg.GTextures
import fnldg.g.GBatch
import fnldg.g.GRnd
import fnldg.g.GTime

class FontPixel private constructor() {

  lateinit var colors: FloatArray
  lateinit var colors3d: FloatArray
  var index = 0
  var next = GRnd.nextInt(48)
  var x = 0f
  var y = 0f
  var anchorX = 0f
  var anchorY = 0f
  private var speedX = 0f
  private var speedY = 0f
  private var indexIncreaseInt = 1 + GRnd.nextInt(19)

  fun drawTextDisplay(batch: GBatch, xOffset: Float, yOffset: Float, fontSize: FontSize): Boolean {
    index = MathUtils.clamp(index, 0, 4)
    if (GTime.alternate) {
      x += speedX
      y += speedY
      speedX *= GTime.oneMinusD5
      speedY *= GTime.oneMinusD5
      x -= maxSpeed.coerceAtMost((x - anchorX) / anchorStrength)
      y -= maxSpeed.coerceAtMost((y - anchorY) / anchorStrength)
    }
    batch.setColor(colors3d[index])
    batch.draw(GTextures.pixel, x, y, fontSize.w3d, fontSize.w3d)
    batch.setColor(colors[index])
    batch.draw(GTextures.pixel, xOffset + x, yOffset + y, fontSize.pixelW, fontSize.pixelW)
    if (GTime.to20 == indexIncreaseInt)
      index++
    return false
  }

  fun free() {
    free(this)
  }

  companion object {
    private val pool = object : Pool<FontPixel>() {
      override fun newObject(): FontPixel {
        return FontPixel()
      }
    }
    const val anchorStrength = 2.6f
    const val maxSpeed = 8f

    fun free(p: FontPixel) {
      pool.free(p)
    }

    fun obtain(elemIndex: Int): FontPixel {
      val p = pool.obtain()
//            p.colors = elems.bright
//            p.colors3d = elems.bright
      if (GRnd.nextBoolean())
        p.x = GRnd.gauss(5f)
      else
        p.y = GRnd.gauss(5f)
      return p
    }
  }
}