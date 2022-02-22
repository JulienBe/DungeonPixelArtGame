package fnldg.g

import java.util.Random
import kotlin.math.abs

object GRnd: Random() {

  fun gauss(f: Float): Float {
    return (nextGaussian() * f).toFloat()
  }
  fun gauss(i: Int): Int {
    return (nextGaussian() * i).toInt()
  }

  fun float(min: Float, max: Float): Float {
    return min + (nextFloat() * (max - min))
  }

  fun int(min: Int, max: Int): Int {
    return min + nextInt(max - min)
  }

  fun absGauss(fl: Float): Float {
    return abs(gauss(fl))
  }

  fun int(max: Int): Int {
    return nextInt(max)
  }
}