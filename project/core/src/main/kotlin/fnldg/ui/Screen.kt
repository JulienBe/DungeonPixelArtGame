package fnldg.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import fnldg.Display
import fnldg.input.Input
import fnldg.g.GBatch
import fnldg.g.GTextures
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

object Screen : KtxScreen {
  val pixelSize = Gdx.graphics.height.toFloat() / 44f
  private val cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
  private val image = Texture("run1.png".toInternalFile(), true).apply { setFilter(
    Texture.TextureFilter.Linear,
    Texture.TextureFilter.Linear
  ) }
  private val selectedImage = Texture("selected.png".toInternalFile(), true).apply { setFilter(
    Texture.TextureFilter.Nearest,
    Texture.TextureFilter.Nearest
  ) }
  private val batch = GBatch()
  private val input = Input()
  val imagePixel = ImagePixel(image)

  override fun show() {
    super.show()
    GTextures
    cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f)
    Gdx.input.inputProcessor = input
  }

  override fun render(delta: Float) {
    clearScreen(0.7f, 0.7f, 0.7f)
    input.newFrame()
    cam.update()
    batch.use(cam.combined) { b ->
      imagePixel.particles.forEach { p ->
        batch.color = batch.color.set(p.color)
        b.draw(GTextures.pixel, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
      }
      imagePixel.selectedParticles.forEach { p ->
        batch.color = batch.color.set(p.color)
        b.draw(selectedImage, p.xf * pixelSize, p.yf * pixelSize, pixelSize, pixelSize)
      }
      Display.act(b)
    }
  }

  override fun dispose() {
    image.disposeSafely()
    batch.disposeSafely()
  }

}
