package fnldg.g

import ktx.assets.pool

class GPool<T>(obtain: () -> T) {
  private val pool = pool { obtain.invoke() }
  fun obtain(): T = pool.obtain()
  fun free(t: T) = pool.free(t)
}