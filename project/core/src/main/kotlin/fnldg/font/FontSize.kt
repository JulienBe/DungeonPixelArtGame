package fnldg.font

data class FontSize(val width: Float) {
  val pixelW = width * 0.8f
  val w3d = width * 1.0f
  val trailW = pixelW
  val fontW: Float = width * PicoFont.w
}