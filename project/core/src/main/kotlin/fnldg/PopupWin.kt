package fnldg

import com.badlogic.gdx.math.Rectangle

class PopupWin(x: Float, y: Float) {
  val rect = Rectangle(x * FirstScreen.pixelSize, y * FirstScreen.pixelSize, FirstScreen.pixelSize * 50, FirstScreen.pixelSize * 10)
}