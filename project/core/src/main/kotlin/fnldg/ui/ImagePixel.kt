package fnldg.ui

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import fnldg.Particle
import ktx.collections.GdxArray

class ImagePixel(image: Texture) {
  val particles = Particle.pixmapToParticles(getPixMap(image))
  val selectedParticles: GdxArray<Particle> = GdxArray()

  fun getPixMap(t: Texture): Pixmap {
    val td = t.textureData
    td.prepare()
    return td.consumePixmap()
  }
}