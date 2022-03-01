package fnldg.font

import com.badlogic.gdx.math.MathUtils
import ktx.assets.pool
import fnldg.colors.Palette
import fnldg.g.GTextures
import fnldg.g.GBatch
import fnldg.g.GRnd
import fnldg.g.GTime

class FontPixel private constructor() {

  var colors: FloatArray = floatArrayOf(Palette.BLUE.f)
  var colors3d: FloatArray = floatArrayOf(Palette.PINK.f)
  var maxIndex = 0
  var next = GRnd.nextInt(48)
  var x = 0f
  var y = 0f
  var anchorX = 0f
  var anchorY = 0f
  private var index = 0
  private var speedX = 0f
  private var speedY = 0f
  private var indexIncreaseInt = 1 + GRnd.nextInt(19)

  fun display(batch: GBatch, xOffset: Float, yOffset: Float, size: FontPixelSize): Boolean {
    index = MathUtils.clamp(index, 0, maxIndex)
    if (GTime.alternate) {
      x += speedX
      y += speedY
      speedX *= GTime.oneMinusD5
      speedY *= GTime.oneMinusD5
      x -= maxSpeed.coerceAtMost((x - anchorX) / anchorStrength)
      y -= maxSpeed.coerceAtMost((y - anchorY) / anchorStrength)
    }
    batch.setColor(colors3d[index])
    batch.draw(GTextures.pixel, xOffset + x, yOffset + y, size.w3d, size.w3d)
    batch.setColor(colors[index])
    batch.draw(GTextures.pixel, xOffset + x, yOffset + y, size.wF, size.wF)
    if (GTime.to20 == indexIncreaseInt)
      index++
    return false
  }

  fun free() {
    free(this)
  }

  companion object {
    private val pool = pool { FontPixel() }
    const val anchorStrength = 2.6f
    const val maxSpeed = 8f

    fun free(p: FontPixel) {
      pool.free(p)
    }

    fun obtain(): FontPixel {
      val p = pool.obtain()
      p.colors = floatArrayOf(Palette.BLUE.f)
      p.colors3d = floatArrayOf(Palette.PINK.f)
      p.maxIndex = p.colors.size - 1
      return p
    }
  }
}