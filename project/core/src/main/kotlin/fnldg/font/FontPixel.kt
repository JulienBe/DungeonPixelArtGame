package fnldg.font

import com.badlogic.gdx.math.MathUtils
import fnldg.colors.Palette
import fnldg.g.*

class FontPixel private constructor() {

  private var colors: FloatArray = floatArrayOf(Palette.BLUE.f)
  private var colors3d: FloatArray = floatArrayOf(Palette.LIGHT_GREY.f)
  private var maxIndex = 0
  private var next = GRnd.nextInt(48)
  private var anchorX = 0f
  private var anchorY = 0f
  private var index = 0
  private var speedX = 0f
  private var speedY = 0f
  private var indexIncreaseInt = 1 + GRnd.nextInt(19)
  var x = 0f
  var y = 0f

  fun init(): FontPixel {
    colors = floatArrayOf(Palette.BLUE.f)
    colors3d = floatArrayOf(Palette.PINK.f)
    maxIndex = colors.size - 1
    return this
  }

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

  fun setAnchor(x: Float, y: Float) {
    this.anchorX = x
    this.anchorY = y
  }

  fun setXY(x: Float, y: Float) {
    this.x = x
    this.y = y
  }

  companion object {
    val pool = GPool { FontPixel() }
    const val anchorStrength = 2.6f
    const val maxSpeed = 8f
  }
}