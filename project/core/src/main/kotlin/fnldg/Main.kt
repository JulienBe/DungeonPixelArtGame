package fnldg

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.collections.GdxArray
import ktx.graphics.use

class Main : KtxGame<KtxScreen>() {
  override fun create() {
    addScreen(FirstScreen())
    setScreen<FirstScreen>()
  }
}

class FirstScreen : KtxScreen {
  private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
  private val batch = SpriteBatch()
  private val pixmap = getPixMap(image)
  private val particles = Particle.pixmapToParticles(pixmap)

  fun getPixMap(t: Texture): Pixmap {
    val td = t.textureData
    td.prepare()
    return td.consumePixmap()
  }

  override fun render(delta: Float) {
    clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
    batch.use { b ->
      particles.forEach { p ->
        batch.color.set(p.color)
        b.draw(image, p.xf, p.xy, 1f, 1f)
      }
    }
  }

  override fun dispose() {
    image.disposeSafely()
    batch.disposeSafely()
  }
}
