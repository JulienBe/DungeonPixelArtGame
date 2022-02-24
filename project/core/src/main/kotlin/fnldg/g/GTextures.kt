package fnldg.g

import com.badlogic.gdx.graphics.Texture
import ktx.assets.toInternalFile

object GTextures {
  val pixel = Texture("square.png".toInternalFile(), true).apply { setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest) }

  fun load() {
  }
}