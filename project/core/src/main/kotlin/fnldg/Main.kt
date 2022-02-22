package fnldg

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.Texture.TextureFilter.Nearest
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

class Main : KtxGame<KtxScreen>() {
  override fun create() {
    addScreen(FirstScreen())
    setScreen<FirstScreen>()
  }
}

class FirstScreen : KtxScreen {
  private val cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
  private val image = Texture("run1.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
  private val selectedImage = Texture("selected.png".toInternalFile(), true).apply { setFilter(Nearest, Nearest) }
  private val batch = SpriteBatch()
  private val sharedState = BooooooooSharedState(image)

  override fun show() {
    super.show()
    GTextures.load()
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f)
    Gdx.input.inputProcessor = Input(sharedState)
  }

  override fun render(delta: Float) {
    clearScreen(0.7f, 0.7f, 0.7f)
    cam.update()
    batch.use(cam.combined) { b ->
      sharedState.particles.forEach { p ->
        batch.color = batch.color.set(p.color)
        b.draw(GTextures.pixel, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
      }
      sharedState.selectedParticles.forEach { p ->
        batch.color = batch.color.set(p.color)
        b.draw(selectedImage, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
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
