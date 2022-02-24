package fnldg.g

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import kotlin.math.roundToInt

class GBatch : SpriteBatch() {

  fun draw(tr: TextureRegion, x: Int, y: Int, w: Float, h: Float) {
    draw(tr, x.toFloat(), y.toFloat(), w, h)
  }

  fun draw(tr: TextureRegion, x: Float, y: Float, w: Float, h: Float, angle: Float) {
    draw(tr, x, y, w / 2f, h / 2f, w, h, 1f, 1f, angle)
  }

  fun setColor(fl: Float) {
    packedColor = fl
  }

  fun drawInt(tr: TextureRegion, x: Float, y: Float, w: Float, h: Float) {
    draw(tr, (x.roundToInt()).toFloat(), (y.roundToInt()).toFloat(), (w.roundToInt()).toFloat(), (h.roundToInt()).toFloat())
  }

  fun drawIntOffset(tr: TextureRegion, x: Float, y: Float, w: Float, h: Float, offsetX: Float, offsetY: Float) {
    draw(tr, (x.roundToInt()).toFloat(), (y.roundToInt()).toFloat(), (w.roundToInt()).toFloat() + offsetX, (h.roundToInt()).toFloat() + offsetY)
  }
}