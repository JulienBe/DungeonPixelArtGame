@file:JvmName("Lwjgl3Launcher")

package fnldg.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import fnldg.Main

/** Launches the desktop (LWJGL3) application. */
fun main() {
  Lwjgl3Application(Main(), Lwjgl3ApplicationConfiguration().apply {
    setTitle("FinalDungeon")
    useVsync(true)
//    setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode())
    setWindowedMode(1440, 800)
    setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
  })
}
