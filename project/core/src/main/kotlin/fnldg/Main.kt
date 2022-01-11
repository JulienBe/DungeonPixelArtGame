package fnldg

import com.badlogic.gdx.graphics.OrthographicCamera
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
  private val cam = OrthographicCamera(640f, 480f)
  private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
  private val batch = SpriteBatch()
  private val particles = Particle.pixmapToParticles(getPixMap(image))

  override fun show() {
    super.show()
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f)
  }

  fun getPixMap(t: Texture): Pixmap {
    val td = t.textureData
    td.prepare()
    return td.consumePixmap()
  }

  override fun render(delta: Float) {
    clearScreen(0.7f, 0.7f, 0.7f)
    cam.update()
    batch.use(cam.combined) { b ->
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
