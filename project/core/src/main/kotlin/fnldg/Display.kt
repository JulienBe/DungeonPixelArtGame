package fnldg

import fnldg.g.GBatch
import ktx.collections.GdxArray
import ktx.collections.GdxMap

interface Display {
  fun display(batch: GBatch)

  companion object {
    private val displays = GdxMap<Layer, GdxArray<Display>>()

    init {
      Layer.values().forEach { layer ->
        displays.put(layer, GdxArray())
      }
    }

    fun add(display: Display, layer: Layer) = displays.get(layer).add(display)
    fun act(batch: GBatch) {
      Layer.values().forEach { layer ->
        displays.get(layer).forEach {
          it.display(batch)
        }
      }
    }
  }
}

enum class Layer {
  UI
}