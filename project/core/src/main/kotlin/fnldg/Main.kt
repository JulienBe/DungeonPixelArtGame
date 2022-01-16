package fnldg

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.Texture.TextureFilter.Nearest
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.collections.GdxArray
import ktx.collections.contains
import ktx.graphics.use

class Main : KtxGame<KtxScreen>() {
  override fun create() {
    addScreen(FirstScreen())
    setScreen<FirstScreen>()
  }
}

class FirstScreen : KtxScreen {
  private val cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
  private val particleImage = Texture("square.png".toInternalFile(), true).apply { setFilter(Nearest, Nearest) }
  private val image = Texture("run1.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
  private val selectedImage = Texture("selected.png".toInternalFile(), true).apply { setFilter(Nearest, Nearest) }
  private val batch = SpriteBatch()
  private val particles = Particle.pixmapToParticles(getPixMap(image))
  private val selectedParticles = GdxArray<Particle>()

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
        batch.color = batch.color.set(p.color)
        b.draw(particleImage, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
      }
      selectedParticles.forEach { p ->
        batch.color = batch.color.set(p.color)
        b.draw(selectedImage, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
      }
    }
    if (Gdx.input.justTouched()) {
      val y: Float = (Gdx.graphics.height - Gdx.input.y).toFloat()
      val x: Float = Gdx.input.x.toFloat()
      val clicked = particles.firstOrNull { it.rect.contains(x, y) }
      if (clicked != null) {
        if (selectedParticles.contains(clicked, true))
          selectedParticles.removeValue(clicked, true)
        else
          selectedParticles.add(clicked)
      }
    }
  }

  override fun dispose() {
    image.disposeSafely()
    batch.disposeSafely()
  }

  companion object {
    val pixelSize = 34f
  }
}
