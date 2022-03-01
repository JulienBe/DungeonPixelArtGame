package fnldg

import fnldg.ui.Screen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class Main : KtxGame<KtxScreen>() {
  override fun create() {
    addScreen(Screen)
    setScreen<Screen>()
  }
}