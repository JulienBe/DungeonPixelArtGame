package fnldg.font

// I will draw to a frame buffer with fixed resolution, that will then be upscaled. So compile time values are ok here
enum class FontPixelSize(val w: Int) {
  SMALL(3), NORMAL(5), BIG(7), HYPER(11);
  val wF = w.toFloat()
  val w3d = wF + 1.0f
  val wPlusSpace = ((wF * 1.333).toInt()).toFloat()
}